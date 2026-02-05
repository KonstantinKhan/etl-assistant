package com.khan366kos.frontend.kotlin.react.app

import mui.material.Alert
import mui.material.AlertColor
import mui.material.Box
import mui.material.CircularProgress
import mui.material.Container
import mui.material.Typography
import mui.system.sx
import react.FC
import react.Props
import web.cssom.AlignItems
import web.cssom.Display
import web.cssom.JustifyContent
import web.cssom.rem

external interface AuthScreenProps : Props {
    var component: AuthComponent
}

val AuthScreen = FC<AuthScreenProps> { props ->
    val component = props.component

    val authState = useCollectState(component.authState)
    val storageState = useCollectState(component.storageState)

    Container {
        sx { marginTop = 4.rem }

        AuthorizationForm {
            storageOptions = when (val storage = storageState) {
                is StorageLoadingState.Success -> storage.storageDefinitions
                else -> emptyList()
            }

            loading = authState is AuthorizationUiState.Loading

            error = when (val state = authState) {
                is AuthorizationUiState.Error -> state.message
                else -> null
            }

            storageLoading = storageState is StorageLoadingState.Loading ||
                    storageState is StorageLoadingState.Initial

            storageError = when (val storage = storageState) {
                is StorageLoadingState.Error -> storage.message
                else -> null
            }

            onSubmit = { username, password, storageId ->
                component.submitAuthorization(username, password, storageId)
            }
        }

        if (authState is AuthorizationUiState.Success) {
            Box {
                sx { marginTop = 2.rem }
                Alert {
                    severity = AlertColor.success.toString()
                    +authState.message
                }
            }
        }
    }
}