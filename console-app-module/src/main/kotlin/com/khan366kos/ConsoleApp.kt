package com.khan366kos

import com.khan366kos.bff.PolynomClient
import com.khan366kos.bff.createSimpleBffClient
import com.khan366kos.common.models.business.Identifier
import com.khan366kos.common.models.business.InnerElement
import com.khan366kos.common.models.simple.ElementName
import com.khan366kos.common.models.simple.GroupId
import com.khan366kos.common.models.simple.ObjectId
import com.khan366kos.common.models.simple.TypeId
import com.khan366kos.common.models.values.StringPropertyValues
import com.khan366kos.common.models.values.Values
import com.khan366kos.common.requests.CreateElementRequest
import com.khan366kos.common.requests.PropertyAssignmentRequest
import com.khan366kos.common.requests.PropertyValueAssignment
import com.khan366kos.mdm.bff.Parser
import khan366kos.excel.handler.ExcelHandler
import kotlinx.coroutines.runBlocking
import java.io.File

fun getFileFromInput(): String? {
    print("Введите путь к Excel файлу: ")
    val filePath = readlnOrNull()?.trim()

    if (filePath.isNullOrEmpty()) {
        println("Путь к файлу не может быть пустым.")
        return null
    }

    val file = File(filePath)
    if (!file.exists()) {
        println("Файл не найден: $filePath")
        return null
    }

    if (!file.isFile) {
        println("Указанный путь не является файлом: $filePath")
        return null
    }

    return filePath
}

fun extractAndFilterBolts(excelHandler: ExcelHandler, filePath: String): Array<String> {
    // Get all values from the 4th column (index 3), excluding the header
    val columnValues = excelHandler.getColumnData(filePath, 3) // 4th column has index 3

    // Filter values that start with "Болт М" and end with "ГОСТ 7805-70"
    val filteredValues = columnValues.filter { value ->
        value.trim().startsWith("Болт М") && value.trim().endsWith("ГОСТ 7805-70")
    }

    return filteredValues.toTypedArray()
}

suspend fun processBoltSpecifications(polynomClient: PolynomClient, parser: Parser) {
    val filePath = getFileFromInput() ?: return
    val excelHandler = ExcelHandler()
    val groupId = GroupId(2866)

    try {
        val boltSpecifications = extractAndFilterBolts(excelHandler, filePath)

        if (boltSpecifications.isNotEmpty()) {
            boltSpecifications.take(100).forEach { bolt ->
                polynomClient.element(
                    CreateElementRequest(
                        groupId, name = ElementName(parser.parsePartData(bolt).toFormattedString())
                    )
                )
            }
            println("Внесено 100 болтов")
        } else {
            println("Не найдено записей, соответствующих критериям: начинающихся с 'Болт М' и заканчивающихся 'ГОСТ 7805-70'")
        }
    } catch (e: Exception) {
        println("Ошибка при обработке Excel файла: ${e.message}")
        e.printStackTrace()
    }
}

suspend fun getAndShowSheetNames(polynomClient: PolynomClient) {
    try {
        val infoObjects = polynomClient.elementByGroup(GroupId(2866))
        val group = "SHORTLOC:Root±30±20±10±80±10±©{}30±©{BASE}130±30"
        val elements =
            infoObjects.map {
                InnerElement(
                    ElementName(
                        "$group${it.name.asString()}"
                    ), Identifier(it.objectId, it.typeId)
                )
            }


        // Тут присваиваем атрибуты для связи с библиотекой компонентов. Не работает сразу после создания объектов.
        // Даже если поставить задержку. Поэтому вызывать отдельно.
        elements.forEach { element ->
            polynomClient.setPropertyValues(
                PropertyAssignmentRequest(
                    values = Values(
                        stringProperties = listOf(
                            StringPropertyValues(
                                objectId = ObjectId(Int.MIN_VALUE),
                                typeId = TypeId(25),
                                value = element.name.asString()
                            )
                        )
                    ), properties = listOf(
                        PropertyValueAssignment(
                            value = Identifier(
                                objectId = ObjectId(Int.MIN_VALUE), typeId = TypeId(25)
                            ), contract = Identifier(
                                objectId = ObjectId(15), typeId = TypeId(38)
                            ), definition = Identifier(
                                objectId = ObjectId(17), typeId = TypeId(9)
                            )
                        )
                    ), propertyOwner = element.identifier
                )
            )
        }


    } catch (e: Exception) {
        println("Error calling API: ${e.message}")
    } finally {
        polynomClient.close()
    }
}

fun main(): Unit = runBlocking {
    val polynomClient: PolynomClient = createSimpleBffClient()
    val parser = Parser()
    val excelHandler = ExcelHandler()

    var continueExecution = true
    while (continueExecution) {
        println("\n--- Меню приложения ---")
        println("1. Выбрать Excel файл и отобразить наименования листов")
        println("2. Выполнить парсинг болтов (существующая функциональность)")
        println("3. Извлечь и отфильтровать болты из Excel файла")
        println("4. Выйти из приложения")
        print("Выберите опцию (1-4): ")

        when (readlnOrNull()) {
            "1" -> {
                getAndShowSheetNames(polynomClient)
            }

            "2" -> {
                val group = "SHORTLOC:Root±30±20±10±80±10±©{}30±©{BASE}130±30"
                val groupId = GroupId(2866)
                val bolts = listOf(
                    "Болт М14х50.23.20Х13 ГОСТ 7805-70",
                    "Болт М4-6gх14 ГОСТ 7805-70",
                    "Болт М16х1,5-6gх25.66.019 ГОСТ 7805-70",
                    "Болт М6-6gх16.23.14Х17Н2.086 ГОСТ 7805-70",
                    "Болт М8-6gх30.23.20Х13.11 ГОСТ 7805-70",
                    "Болт М8-6gх55.88.019 ГОСТ 7805-70",
                    //        "Болт М12х35-66.01 ГОСТ 7805-70", // непонятная маркировка
                    "Болт М5-6gх14.23.14Х17Н2 ГОСТ 7805-70",
                    //        "Болт М10-6gх36.88.019 ГОСТ 7805-70", // нет такой длины
                    //        "Болт М14-6gх42.88.019 ГОСТ 7805-70" // нет такой длины
                    "Болт М10-6gх16.12Х18Н10Т.11 ГОСТ 7805-70",
                    "Болт М8-6gх16.21.12Х18Н10Т.11 ГОСТ 7805-70",
                    "Болт М8-6gх65.88.019 ГОСТ 7805-70",
                    "Болт М6-6gх12.24.10Х11Н23Т3МР.086 ГОСТ 7805-70"
                )

                val parseBolts = bolts.map { bolt ->
                    parser.parsePartData(bolt).toFormattedString()
                }

                parseBolts.forEach { println(it) }

                val sheets = excelHandler.getAllSheetNames("C:\\Users\\han\\Desktop\\Классификатор.xlsx")
                println("\nЛисты в тестовом файле:")
                sheets.forEach { println(it) }
            }

            "3" -> {
                processBoltSpecifications(polynomClient, parser)
            }

            "4" -> {
                continueExecution = false
                println("Завершение работы приложения...")
            }

            else -> {
                println("Неверный выбор. Пожалуйста, выберите 1, 2, 3 или 4.")
            }
        }

//    try {
//        val infoObjects = polynomClient.elementByGroup(GroupId(2866))
//        val elements = infoObjects.map { InnerElement(it.name, Identifier(it.objectId, it.typeId)) }

        // Тут создаём записи болтов. Временное решение для тестирования и разработки.
//        parseBolts.forEach { bolt ->
//            polynomClient.element(
//                CreateElementRequest(
//                    groupId,
//                    name = ElementName(bolt)
//                )
//            )
//        }

        // Тут присваиваем атрибуты для связи с библиотекой компонентов. Не работает сразу после создания объектов.
        // Даже если поставить задержку. Поэтому вызывать отдельно.
//        elements.forEach { element ->
//            polynomClient.setPropertyValues(
//                PropertyAssignmentRequest(
//                    values = Values(
//                        stringProperties = listOf(
//                            StringPropertyValues(
//                                objectId = ObjectId(Int.MIN_VALUE),
//                                typeId = TypeId(25),
//                                value = group + element.name.asString()
//                            )
//                        )
//                    ),
//                    properties = listOf(
//                        PropertyValueAssignment(
//                            value = Identifier(
//                                objectId = ObjectId(Int.MIN_VALUE),
//                                typeId = TypeId(25)
//                            ),
//                            contract = Identifier(
//                                objectId = ObjectId(15),
//                                typeId = TypeId(38)
//                            ),
//                            definition = Identifier(
//                                objectId = ObjectId(17),
//                                typeId = TypeId(9)
//                            )
//                        )
//                    ),
//                    propertyOwner = element.identifier
//                )
//            )
//        }
//
//
//    } catch (e: Exception) {
//        println("Error calling API: ${e.message}")
//    } finally {
//        polynomClient.close()
//    }
    }

    try {
        // Закрываем клиента при завершении работы
        polynomClient.close()
    } catch (e: Exception) {
        println("Error closing API client: ${e.message}")
    }
}