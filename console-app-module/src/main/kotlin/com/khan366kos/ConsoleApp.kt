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
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val polynomClient: PolynomClient = createSimpleBffClient()
    val parser = Parser()
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

    try {
        val infoObjects = polynomClient.elementByGroup(GroupId(2866))
        val elements = infoObjects.map { InnerElement(it.name, Identifier(it.objectId, it.typeId)) }

//        parseBolts.forEach { bolt ->
//            polynomClient.element(
//                CreateElementRequest(
//                    groupId,
//                    name = ElementName(bolt)
//                )
//            )
//        }

        elements.forEach { element ->
            polynomClient.setPropertyValues(
                PropertyAssignmentRequest(
                    values = Values(
                        stringProperties = listOf(
                            StringPropertyValues(
                                objectId = ObjectId(Int.MIN_VALUE),
                                typeId = TypeId(25),
                                value = group + element.name.asString()
                            )
                        )
                    ),
                    properties = listOf(
                        PropertyValueAssignment(
                            value = Identifier(
                                objectId = ObjectId(Int.MIN_VALUE),
                                typeId = TypeId(25)
                            ),
                            contract = Identifier(
                                objectId = ObjectId(15),
                                typeId = TypeId(38)
                            ),
                            definition = Identifier(
                                objectId = ObjectId(17),
                                typeId = TypeId(9)
                            )
                        )
                    ),
                    propertyOwner = element.identifier
                )
            )
        }


    } catch (e: Exception) {
        println("Error calling API: ${e.message}")
    } finally {
        polynomClient.close()
    }
}