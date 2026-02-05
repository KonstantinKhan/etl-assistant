package com.khan366kos.frontend.kotlin.react.app

import mui.material.Alert
import mui.material.Button
import mui.material.CircularProgress
import mui.material.Container
import mui.material.Typography
import react.FC
import react.Props

external interface WorkbookScreenProps : Props {
    var component: WorkbookComponent
}

val WorkbookScreen = FC<WorkbookScreenProps> { props ->
    val component = props.component

    val uiState = useCollectState(component.state)

    Container {
        FileUpload {
            disabled = uiState is WorkbookUiState.Loading
            onFileSelected = { file -> component.uploadFile(file) }
        }

        Button {
            onClick = { component.loadSheets() }
            disabled = uiState is WorkbookUiState.Loading
            +"Загрузить демо-файл"
        }

        when (uiState) {
            is WorkbookUiState.Loading -> CircularProgress()
            is WorkbookUiState.Success -> SheetsDisplay { workbook = (uiState as WorkbookUiState.Success).workbook }
            is WorkbookUiState.Error -> Alert { +"Ошибка: ${(uiState as WorkbookUiState.Error).message}" }
            WorkbookUiState.Initial -> Typography { +"Выберите файл или загрузите демо-файл" }
        }
    }
}