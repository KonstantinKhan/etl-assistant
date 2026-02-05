package com.khan366kos.frontend.kotlin.react.app

import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import mui.material.*
import mui.material.styles.TypographyVariant.Companion.h1
import mui.system.sx
import react.FC
import react.Props
import react.useEffectOnce
import react.useMemo
import web.cssom.*

val App = FC<Props> {

    val rootComponent = useMemo {
        RootComponent(DefaultComponentContext(LifecycleRegistry()))
    }

    @Suppress("UNUSED_LAMBDA_EXPRESSION")
    useEffectOnce {
        ;
        {
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

        val childStack = useValue(rootComponent.childStack)

        when (val child = childStack.active.instance) {
            is RootComponent.Child.Auth -> AuthScreen {
                component = child.component
            }
            is RootComponent.Child.Workbook -> WorkbookScreen {
                component = child.component
            }
            is RootComponent.Child.ReferenceList -> ReferenceListScreen {
                component = child.component
            }
        }
    }
}
