package com.khan366kos.frontend.kotlin.react.app

import com.khan366kos.common.models.business.Reference
import mui.material.*
import mui.material.styles.TypographyVariant
import mui.system.sx
import react.FC
import react.Props
import react.ReactNode
import web.cssom.*

external interface ReferenceListDisplayProps : Props {
    var references: List<Reference>
}

val ReferenceListDisplay = FC<ReferenceListDisplayProps> { props ->
    Box {
        sx {
            display = Display.flex
            flexDirection = FlexDirection.column
            gap = 2.rem
        }

        Typography {
            variant = TypographyVariant.body1
            +"Всего справочников: ${props.references.size}"
        }

        props.references.forEach { reference ->
            Card {
                sx { marginTop = 1.rem }

                CardContent {
                    Typography {
                        variant = TypographyVariant.h6
                        +reference.name.asString()
                    }

                    Typography {
                        variant = TypographyVariant.body2
                        sx { color = Color("text.secondary") }
                        +reference.description.asString()
                    }

                    Typography {
                        variant = TypographyVariant.caption
                        +"ID: ${reference.id.asString()} | Object ID: ${reference.objectId.asString()} | Type ID: ${reference.typeId.asString()}"
                    }

                    Chip {
                        label = ReactNode(if (reference.writeAccess.asBoolean()) "Запись разрешена" else "Только чтение")
                        color = if (reference.writeAccess.asBoolean()) ChipColor.success else ChipColor.default
                        size = Size.small
                    }
                }
            }
        }
    }
}