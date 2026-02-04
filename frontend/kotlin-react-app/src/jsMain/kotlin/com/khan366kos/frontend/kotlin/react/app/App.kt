package com.khan366kos.frontend.kotlin.react.app

import mui.material.*
import mui.material.styles.TypographyVariant.Companion.h1
import mui.system.sx
import react.FC
import react.Props
import react.useEffectOnce
import react.useMemo
import web.cssom.*

val App = FC<Props> {

    val viewModel = useMemo { WorkbookViewModel() }
    val authorizationViewModel = useMemo { AuthorizationViewModel() }

    val uiState = useCollectState(viewModel.state)
    val authState = useCollectState(authorizationViewModel.authState)
    val storageState = useCollectState(authorizationViewModel.storageState)

    @Suppress("UNUSED_LAMBDA_EXPRESSION")
    useEffectOnce {
        authorizationViewModel.loadStorageDefinitions()
        ;
        {
            viewModel.cleanup()
            authorizationViewModel.cleanup()
            ApiClient.close()
        }
    }

    Container {
        sx {
            marginTop = 4.rem
            marginBottom = 4.rem
        }

        Typography {
            variant = h1
            sx { marginBottom = 3.rem }
            +"ETL Assistant"
        }

        FileUpload {
            disabled = uiState is WorkbookUiState.Loading
            onFileSelected = { file ->
                viewModel.uploadFile(file)
            }
        }

        Button {
            variant = ButtonVariant.outlined
            onClick = { viewModel.loadSheets() }
            disabled = uiState is WorkbookUiState.Loading
            sx { marginBottom = 2.rem }
            +"Загрузить демо-файл"
        }

        when (val state = uiState) {
            is WorkbookUiState.Initial -> {
                Typography {
                    sx { color = Color("text.secondary") }
                    +"Выберите файл или загрузите демо-файл"
                }
            }

            is WorkbookUiState.Loading -> {
                Box {
                    sx {
                        display = Display.flex
                        alignItems = AlignItems.center
                        gap = 2.rem
                    }
                    CircularProgress()
                    Typography { +"Обработка файла..." }
                }
            }

            is WorkbookUiState.Error -> {
                Alert {
                    severity = AlertColor.error.toString()
                    +"Ошибка: ${state.message}"
                }
            }

            is WorkbookUiState.Success -> {
                SheetsDisplay {
                    workbook = state.workbook
                }
            }
        }

        // Authorization Section
        Box {
            sx { marginTop = 4.rem }

            when (val storage = storageState) {
                is StorageLoadingState.Loading -> {
                    Box {
                        sx {
                            display = Display.flex
                            alignItems = AlignItems.center
                            gap = 2.rem
                            justifyContent = JustifyContent.center
                        }
                        CircularProgress()
                        Typography { +"Загрузка списка хранилищ..." }
                    }
                }

                is StorageLoadingState.Error -> {
                    Alert {
                        severity = AlertColor.error.toString()
                        +"Ошибка загрузки хранилищ: ${storage.message}"
                    }
                }

                is StorageLoadingState.Success -> {
                    AuthorizationForm {
                        storageOptions = storage.storageDefinitions
                        loading = authState is AuthorizationUiState.Loading
                        error = when (val state = authState) {
                            is AuthorizationUiState.Error -> state.message
                            else -> null
                        }
                        onSubmit = { username, password, storageId ->
                            authorizationViewModel.submitAuthorization(username, password, storageId)
                        }
                    }

                    // Show success message if authorized
                    if (authState is AuthorizationUiState.Success) {
                        Box {
                            sx { marginTop = 2.rem }
                            Alert {
                                severity = AlertColor.success.toString()
                                +(authState as AuthorizationUiState.Success).message
                            }
                        }
                    }
                }

                is StorageLoadingState.Initial -> {
                    Box {
                        sx {
                            display = Display.flex
                            justifyContent = JustifyContent.center
                        }
                        CircularProgress()
                    }
                }
            }
        }
    }
}
