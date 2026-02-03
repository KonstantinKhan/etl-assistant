package com.khan366kos.frontend.kotlin.react.app

import com.khan366kos.etl.assistant.transport.models.StorageDefinitionTransport
import mui.material.*
import mui.material.styles.TypographyVariant
import mui.system.sx
import react.FC
import react.Props
import web.cssom.*

external interface StorageDefinitionsDisplayProps : Props {
    var storageDefinitions: List<StorageDefinitionTransport>
}

val StorageDefinitionsDisplay = FC<StorageDefinitionsDisplayProps> { props ->
    Box {
        sx {
            display = Display.flex
            flexDirection = FlexDirection.column
            gap = 2.rem
        }

        Typography {
            variant = TypographyVariant.h5
            +"Storage Definitions (${props.storageDefinitions.size})"
        }

        props.storageDefinitions.forEach { definition ->
            StorageDefinitionCard {
                this.storageDefinition = definition
            }
        }
    }
}

external interface StorageDefinitionCardProps : Props {
    var storageDefinition: StorageDefinitionTransport
}

val StorageDefinitionCard = FC<StorageDefinitionCardProps> { props ->
    Card {
        sx { marginBottom = 1.rem }

        CardContent {
            Typography {
                variant = TypographyVariant.h6
                sx { marginBottom = 0.5.rem }
                +(props.storageDefinition.displayName ?: props.storageDefinition.storageId)
            }

            Typography {
                variant = TypographyVariant.body2
                sx { color = Color("text.secondary") }
                +"Storage ID: ${props.storageDefinition.storageId}"
            }
        }
    }
}