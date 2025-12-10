package com.khan366kos.common.models.example

import com.khan366kos.common.models.contracts.Contract
import com.khan366kos.common.models.data.PropertyOwner
import com.khan366kos.common.models.definitions.*
import com.khan366kos.common.models.items.Item
import com.khan366kos.common.models.measure.MeasureEntities
import com.khan366kos.common.models.measure.MeasureEntity
import com.khan366kos.common.models.measure.MeasureUnits
import com.khan366kos.common.models.measure.MeasureUnit
import com.khan366kos.common.models.simple.*
import com.khan366kos.common.models.values.*
import com.khan366kos.common.models.Identifier

/**
 * Example of how to use the created models to represent the JSON structure
 */
object JsonDataStructureExample {
    fun createExampleDataStructure(): PropertyOwner {
        return PropertyOwner(
            contracts = listOf(
                Contract(
                    objectId = ObjectId(1),
                    typeId = TypeId(101),
                    name = "Sample Contract",
                    description = "A sample contract for demonstration",
                    properties = emptyList()
                )
            ),
            definitions = Definitions(
                doubleProperties = listOf(
                    DoubleProperty(
                        objectId = ObjectId(2),
                        typeId = TypeId(102),
                        id = "double_sample",
                        name = "Sample Double Property",
                        dataType = 1,
                        description = "A sample double property",
                        code = "SDP001",
                        isSystemObject = false,
                        absoluteCode = "ABS-SDP001",
                        measureEntity = Identifier(ObjectId(10), TypeId(110))
                    )
                ),
                stringProperties = listOf(
                    StringProperty(
                        objectId = ObjectId(3),
                        typeId = TypeId(103),
                        id = "string_sample",
                        name = "Sample String Property",
                        dataType = 2,
                        description = "A sample string property",
                        code = "SSP001",
                        isSystemObject = false,
                        absoluteCode = "ABS-SSP001"
                    )
                ),
                booleanProperties = emptyList(),
                colorProperties = emptyList(),
                opticProperties = emptyList(),
                dateTimeProperties = emptyList(),
                imageProperties = emptyList(),
                rtfProperties = emptyList(),
                enumProperties = emptyList(),
                setProperties = emptyList(),
                integerProperties = emptyList(),
                binaryProperties = emptyList(),
                guidProperties = emptyList(),
                enumBoolProperties = emptyList(),
                enumDoubleProperties = emptyList(),
                enumIntProperties = emptyList(),
                enumStringProperties = emptyList(),
                tableProperties = emptyList()
            ),
            measureEntities = MeasureEntities(
                entities = listOf(
                    MeasureEntity(
                        objectId = ObjectId(10),
                        typeId = TypeId(110),
                        name = "Length",
                        uid = "550e8400-e29b-41d4-a716-446655440000", // UUID as string
                        code = "LEN001",
                        basicUnit = Identifier(ObjectId(11), TypeId(111))
                    )
                )
            ),
            measureUnits = MeasureUnits(
                units = listOf(
                    MeasureUnit(
                        objectId = ObjectId(11),
                        typeId = TypeId(111),
                        name = "Meter",
                        uid = "550e8400-e29b-41d4-a716-446655440001", // UUID as string
                        code = "MTR001",
                        isBasic = true,
                        designation = "m",
                        fromBasicFactor = 1.0,
                        fromBasicOffset = 0.0,
                        measureEntity = Identifier(ObjectId(10), TypeId(110)),
                        codeOkei = "MTR",
                        internationalDesignationOkei = "MTR_CODE",
                        internationalLiteralDesignation = "meter",
                        literalDesignation = "m"
                    )
                )
            ),
            values = Values(
                doubleProperties = listOf(
                    DoublePropertyValues(
                        objectId = ObjectId(20),
                        typeId = TypeId(120),
                        mode = 0,
                        value = 100.0,
                        minValue = 0.0,
                        maxValue = 1000.0,
                        lowerTolerance = -0.5,
                        upperTolerance = 0.5,
                        measureUnit = Identifier(ObjectId(11), TypeId(111))
                    )
                ),
                stringProperties = listOf(
                    StringPropertyValues(
                        objectId = ObjectId(21),
                        typeId = TypeId(121),
                        value = "Sample string value"
                    )
                ),
                booleanProperties = listOf(
                    BooleanPropertyValues(
                        objectId = ObjectId(22),
                        typeId = TypeId(122),
                        value = true
                    )
                ),
                colorProperties = emptyList(),
                opticProperties = emptyList(),
                dateTimeProperties = listOf(
                    DateTimePropertyValues(
                        objectId = ObjectId(25),
                        typeId = TypeId(125),
                        value = "2025-12-10T10:00:00Z", // ISO 8601 string format
                        useTime = true
                    )
                ),
                imageProperties = emptyList(),
                rtfProperties = emptyList(),
                enumProperties = emptyList(),
                setProperties = emptyList(),
                integerProperties = listOf(
                    IntegerPropertyValues(
                        objectId = ObjectId(23),
                        typeId = TypeId(123),
                        value = 42
                    )
                ),
                binaryProperties = emptyList(),
                guidProperties = listOf(
                    GuidPropertyValues(
                        objectId = ObjectId(24),
                        typeId = TypeId(124),
                        value = "550e8400-e29b-41d4-a716-446655440002" // UUID as string
                    )
                ),
                enumBoolProperties = emptyList(),
                enumDoubleProperties = emptyList(),
                enumIntProperties = emptyList(),
                enumStringProperties = emptyList(),
                tableProperties = emptyList()
            ),
            items = listOf(
                Item(
                    objectId = ObjectId(30),
                    typeId = TypeId(130),
                    id = "item-001",
                    selfContracts = emptyList(),
                    properties = emptyList()
                )
            )
        )
    }
}