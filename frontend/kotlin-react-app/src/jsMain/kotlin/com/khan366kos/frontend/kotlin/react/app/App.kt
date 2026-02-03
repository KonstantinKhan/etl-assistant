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
    val storageDefinitionsViewModel = useMemo { StorageDefinitionsViewModel() }

    val uiState = useCollectState(viewModel.state)
    val storageDefinitionsState = useCollectState(storageDefinitionsViewModel.state)

    @Suppress("UNUSED_LAMBDA_EXPRESSION")
    useEffectOnce {
        ;
        {
            viewModel.cleanup()
            storageDefinitionsViewModel.cleanup()
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

        Button {
            variant = ButtonVariant.outlined
            onClick = { storageDefinitionsViewModel.loadStorageDefinitions() }
            disabled = storageDefinitionsState is StorageDefinitionsUiState.Loading
            sx { marginBottom = 2.rem }
            +"Загрузить Storage Definitions"
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

        // Storage Definitions Section
        Box {
            sx { marginTop = 4.rem }

            when (val state = storageDefinitionsState) {
                is StorageDefinitionsUiState.Initial -> {
                    Typography {
                        sx { color = Color("text.secondary") }
                        +"Нажмите кнопку для загрузки Storage Definitions"
                    }
                }

                is StorageDefinitionsUiState.Loading -> {
                    Box {
                        sx {
                            display = Display.flex
                            alignItems = AlignItems.center
                            gap = 2.rem
                        }
                        CircularProgress()
                        Typography { +"Загрузка Storage Definitions..." }
                    }
                }

                is StorageDefinitionsUiState.Error -> {
                    Alert {
                        severity = AlertColor.error.toString()
                        +"Ошибка: ${state.message}"
                    }
                }

                is StorageDefinitionsUiState.Success -> {
                    StorageDefinitionsDisplay {
                        storageDefinitions = state.storageDefinitions
                    }
                }
            }
        }
    }
}
