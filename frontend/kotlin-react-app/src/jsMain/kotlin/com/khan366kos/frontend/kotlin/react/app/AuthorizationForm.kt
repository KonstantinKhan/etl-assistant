package com.khan366kos.frontend.kotlin.react.app

import com.khan366kos.etl.assistant.transport.models.StorageDefinitionTransport
import mui.material.*
import mui.material.styles.TypographyVariant
import mui.system.sx
import react.FC
import react.Props
import react.ReactNode
import react.dom.onChange
import react.useState
import web.cssom.*
import web.html.InputType
import web.html.password

external interface AuthorizationFormProps : Props {
    var storageOptions: List<StorageDefinitionTransport>
    var onSubmit: (username: String, password: String, storageId: String) -> Unit
    var loading: Boolean
    var error: String?
}

val AuthorizationForm = FC<AuthorizationFormProps> { props ->
    var username by useState("")
    var password by useState("")
    var storageId by useState("")

    val isFormValid = username.isNotBlank() && password.isNotBlank() && storageId.isNotBlank()

    Box {
        sx {
            display = Display.flex
            flexDirection = FlexDirection.column
            gap = 2.rem
            maxWidth = 400.px
            margin = Auto.auto
        }

        Typography {
            variant = TypographyVariant.h5
            sx {
                marginBottom = 2.rem
                textAlign = TextAlign.center
            }
            +"Авторизация"
        }

        if (props.error != null) {
            Alert {
                severity = AlertColor.error.toString()
                +props.error!!
            }
        }

        TextField {
            fullWidth = true
            label = ReactNode("Имя пользователя")
            value = username
            disabled = props.loading
            onChange = { event ->
                username = event.target.asDynamic().value as String
            }
            sx {
                marginBottom = 1.rem
            }
        }

        TextField {
            fullWidth = true
            label = ReactNode("Пароль")
            type = InputType.password
            value = password
            disabled = props.loading
            onChange = { event ->
                password = event.target.asDynamic().value as String
            }
            sx {
                marginBottom = 1.rem
            }
        }

        FormControl {
            fullWidth = true
            sx {
                marginBottom = 1.rem
            }

            InputLabel {
                +"Хранилище"
            }

            Select {
                value = storageId.unsafeCast<Nothing?>()
                label = ReactNode("Хранилище")
                disabled = props.loading
                onChange = { event, _ ->
                    storageId = event.target.asDynamic().value as String
                }

                props.storageOptions.forEach { storage ->
                    MenuItem {
                        value = storage.storageId
                        +(storage.displayName ?: storage.storageId)
                    }
                }
            }
        }

        Button {
            variant = ButtonVariant.contained
            fullWidth = true
            disabled = !isFormValid || props.loading
            sx {
                marginTop = 1.rem
                padding = Padding(12.px, 24.px)
            }

            onClick = {
                if (isFormValid) {
                    props.onSubmit(username, password, storageId)
                }
            }

            if (props.loading) {
                CircularProgress {
                    size = 24
                    sx {
                        marginRight = 1.rem
                    }
                }
            }

            +"Войти"
        }
    }
}