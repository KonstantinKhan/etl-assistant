package com.khan366kos.frontend.kotlin.react.app

import com.khan366kos.etl.assistant.transport.models.EtlSheetTransport
import com.khan366kos.etl.assistant.transport.models.EtlWorkbookTransport
import mui.material.*
import mui.material.styles.TypographyVariant
import mui.system.sx
import react.FC
import react.Props
import react.ReactNode
import react.create
import web.cssom.*

external interface SheetsDisplayProps : Props {
    var workbook: EtlWorkbookTransport
}

val SheetsDisplay = FC<SheetsDisplayProps> { props ->
    Box {
        sx {
            display = Display.flex
            flexDirection = FlexDirection.column
            gap = 2.rem
        }

        Typography {
            variant = TypographyVariant.h5
            +"Листы книги (${props.workbook.sheets.size})"
        }

        props.workbook.sheets.forEach { sheet ->
            SheetCard { this.sheet = sheet }
        }
    }
}

external interface SheetCardProps : Props {
    var sheet: EtlSheetTransport
}

val SheetCard = FC<SheetCardProps> { props ->
    Card {
        sx { marginBottom = 1.rem }

        CardContent {
            Typography {
                variant = TypographyVariant.h6
                sx { marginBottom = 1.rem }
                +props.sheet.title
            }

            Typography {
                variant = TypographyVariant.body2
                sx { color = Color("text.secondary") }
                +"Количество записей: ${props.sheet.entriesSize}"
            }

            if (props.sheet.headers.isNotEmpty()) {
                Typography {
                    variant = TypographyVariant.body1
                    sx {
                        marginTop = 1.rem
                        marginBottom = 0.5.rem
                        fontWeight = FontWeight.bold
                    }
                    +"Заголовки:"
                }

                Box {
                    sx {
                        display = Display.flex
                        flexWrap = FlexWrap.wrap
                        gap = 0.5.rem
                    }

                    props.sheet.headers.forEach { header ->
                        Chip {
                            label = ReactNode(header)
                            size = Size.small
                        }
                    }
                }
            }
        }
    }
}
