package com.khan366kos.frontend.kotlin.react.app

import mui.material.*
import mui.material.styles.TypographyVariant
import mui.system.sx
import react.FC
import react.Props
import web.cssom.rem

external interface ReferenceListScreenProps : Props {
    var component: ReferenceListComponent
}

val ReferenceListScreen = FC<ReferenceListScreenProps> { props ->
    val component = props.component
    val uiState = useCollectState(component.state)

    Container {
        sx { marginTop = 4.rem }

        Typography {
            variant = TypographyVariant.h5
            sx { marginBottom = 2.rem }
            +"Список справочников"
        }

        Button {
            onClick = { component.loadReferences() }
            disabled = uiState is ReferenceListUiState.Loading
            variant = ButtonVariant.contained
            +"Обновить"
        }

        when (uiState) {
            is ReferenceListUiState.Loading -> CircularProgress()
            is ReferenceListUiState.Success -> ReferenceListDisplay {
                references = uiState.references
            }
            is ReferenceListUiState.Error -> Alert {
                severity = AlertColor.error.toString()
                +"Ошибка: ${uiState.message}"
            }
            ReferenceListUiState.Initial -> Typography {
                +"Загрузка справочников..."
            }
        }
    }
}