package com.khan366kos.frontend.kotlin.react.app

import mui.material.Box
import mui.material.Button
import mui.material.ButtonVariant
import mui.material.Typography
import mui.material.styles.TypographyVariant.Companion.h1
import mui.material.styles.TypographyVariant.Companion.h2
import react.FC
import react.Props
import react.useEffectOnce
import react.useMemo

val App = FC<Props> {

    val viewModel = useMemo { WorkbookViewModel() }

    val sheets = useCollectState(viewModel.state)

    @Suppress("UNUSED_LAMBDA_EXPRESSION")
    useEffectOnce {
        ;
        {
            viewModel.cleanup()
        }
    }

    Box {
        key = "Container"
        Typography {
            variant = h1
            +"Kotlin React!"
        }

        Button {
            variant = ButtonVariant.contained
            onClick = {
                viewModel.sheets()
            }
            +"Листы"
        }

        Typography {
            variant = h2
            +"$sheets"
        }
    }
}