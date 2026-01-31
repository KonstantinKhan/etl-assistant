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
import com.khan366kos.common.requests.ParentGroup
import com.khan366kos.common.requests.PropertyAssignmentRequest
import com.khan366kos.common.requests.PropertyValueAssignment
import com.khan366kos.etlassistant.logging.logger
import com.khan366kos.parser.partlib.Parser
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withTimeout
import java.io.File

val logger = logger("ConsoleApp")

/**
 * Executes the given block with retry logic.
 *
 * @param maxAttempts Maximum number of attempts including the initial one
 * @param initialDelayMs Initial delay between retries in milliseconds
 * @param factor Exponential backoff factor for delays between retries
 * @param block The suspending function to execute with retries
 * @return The result of the block if successful, or a failure Result if all attempts fail
 */
suspend fun <T> retry(
    maxAttempts: Int = 3,
    initialDelayMs: Long = 1000L,
    factor: Double = 2.0,
    block: suspend () -> T
): Result<T> {
    var currentDelay = initialDelayMs
    var lastException: Exception? = null

    repeat(maxAttempts) { attempt ->
        try {
            val result = block()
            return Result.success(result)
        } catch (e: Exception) {
            lastException = e
            if (attempt < maxAttempts - 1) {
                logger.info("Attempt ${attempt + 1} failed: ${e.message}. Retrying in ${currentDelay}ms...")
                delay(currentDelay)
                currentDelay = (currentDelay * factor).toLong()
            } else {
                logger.info("Attempt ${attempt + 1} failed: ${e.message}. All attempts exhausted.")
            }
        }
    }

    // Return failure result after all attempts
    return Result.failure(lastException ?: RuntimeException("Unknown error after $maxAttempts attempts"))
}

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

//fun extractAndFilterBolts(excelHandler: ExcelHandler, filePath: String): Array<String> {
//    val columnValues = excelHandler.getColumnData(filePath, 3)
//
//    val filteredValues = columnValues.filter { value ->
//        value.trim().startsWith("Болт М") && value.trim().endsWith("ГОСТ 7805-70")
//    }
//
//    return filteredValues.toTypedArray()
//}

suspend fun createObjectsConcurrently(
    polynomClient: PolynomClient,
    parser: Parser,
    groupId: GroupId,
    items: List<String>,
    concurrency: Int = 5,
    delayBetweenBatches: Long = 1000L,
    maxRetryAttempts: Int = 5,
    initialRetryDelay: Long = 1000L,
    retryFactor: Double = 2.0
) = coroutineScope {
    val channel = Channel<String>(Channel.UNLIMITED)
    val semaphore = Semaphore(concurrency)
    val failedItems = mutableListOf<String>()
    val failedItemsMutex = kotlinx.coroutines.sync.Mutex()

    // Launch a coroutine to feed items into the channel
    launch {
        items.forEach { channel.send(it) }
        channel.close()
    }

    // Launch worker coroutines that consume from the channel
    (1..concurrency).map { workerIndex ->
        launch {
            for (item in channel) {
                semaphore.acquire()
                val result = retry(
                    maxAttempts = maxRetryAttempts,
                    initialDelayMs = initialRetryDelay,
                    factor = retryFactor
                ) {
//                    withTimeout(60_000) {
//                        logger.doWithLogging("Create empty polynom objects", item = item) {
//                            polynomClient.element(
//                                CreateElementRequest(
//                                    groupId, name = ElementName(parser.parsePartData(item).toFormattedString())
//                                )
//                            )
//                        }
//                    }
                }

                if (result.isFailure) {
                    failedItemsMutex.withLock {
                        failedItems.add(item)
                    }
                    logger.error(
                        "Failed to create polynom object after $maxRetryAttempts attempts for item: $item", t = result.exceptionOrNull()
                    )
                }

                semaphore.release()
                delay(delayBetweenBatches)
            }
        }
    }.joinAll()

    // Log all failed items after processing
    if (failedItems.isNotEmpty()) {
        logger.info("Items that failed to be processed after all retry attempts: ${failedItems.joinToString(", ")}")
        println("\nСледующие элементы не были обработаны после всех попыток:")
        failedItems.forEach { item ->
            println("- $item")
        }
    } else {
        logger.info("All items were successfully processed.")
        println("\nВсе элементы были успешно обработаны.")
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun processBoltSpecifications(polynomClient: PolynomClient, parser: Parser) {
    val filePath = getFileFromInput() ?: return // C:\Users\han\Desktop\Классификатор.xlsx
//    val excelHandler = ExcelHandler()
    val groupId = GroupId(2866)

    try {
//        val boltSpecifications = extractAndFilterBolts(excelHandler, filePath)
//        println("Элементов для обработки: ${boltSpecifications.size}")

//        createObjectsConcurrently(polynomClient, parser, groupId, boltSpecifications.toList())

//        if (boltSpecifications.isNotEmpty()) {
//            boltSpecifications.forEach { bolt ->
//                logger.doWithLogging("create empty polynom objects") {
//                    polynomClient.element(
//                        CreateElementRequest(
//                            groupId, name = ElementName(parser.parsePartData(bolt).toFormattedString())
//                        )
//                    )
//                }
//            }
//            println("Внесено 10 болтов")
//        } else {
//            println("Не найдено записей, соответствующих критериям: начинающихся с 'Болт М' и заканчивающихся 'ГОСТ 7805-70'")
//        }
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
//    val excelHandler = ExcelHandler()

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

//                val sheets = excelHandler.getAllSheetNames("C:\\Users\\han\\Desktop\\Классификатор.xlsx")
                println("\nЛисты в тестовом файле:")
//                sheets.forEach { println(it) }
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