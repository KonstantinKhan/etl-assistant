package com.khan366kos.frontend.kotlin.react.app

import mui.material.*
import mui.material.styles.TypographyVariant
import mui.system.sx
import react.FC
import react.Props
import react.dom.html.ReactHTML.input
import react.useRef
import react.useState
import web.cssom.*
import web.file.File
import web.html.HTMLInputElement
import web.html.Hidden
import web.html.InputType
import web.html.file
import web.html.`true`

external interface FileUploadProps : Props {
    var onFileSelected: (File) -> Unit
    var disabled: Boolean
}

val FileUpload = FC<FileUploadProps> { props ->
    var fileName by useState<String?>(null)
    val inputRef = useRef<HTMLInputElement>(null)

    Box {
        sx {
            display = Display.flex
            flexDirection = FlexDirection.column
            gap = 1.rem
            marginBottom = 2.rem
        }

        Typography {
            variant = TypographyVariant.h6
            +"Загрузить Excel файл"
        }

        Button {
            variant = ButtonVariant.contained
            disabled = props.disabled

            sx {
                textTransform = "none".unsafeCast<TextTransform>()
            }

            onClick = {
                inputRef.current?.apply {
                    value = ""
                    click()
                }
            }

            +"Выбрать файл (.xlsx)"
        }

        input {
            ref = inputRef
            type = InputType.file
            accept = ".xlsx,.xls"
            hidden = Hidden.`true`


            onChange = { event ->
                val input = event.currentTarget
                val file = input.files?.item(0)
                if (file != null) {
                    fileName = file.name
                    props.onFileSelected(file)
                }
            }
        }

        if (fileName != null) {
            Typography {
                variant = TypographyVariant.body2
                sx { color = Color("text.secondary") }
                +"Выбран файл: $fileName"
            }
        }
    }
}
