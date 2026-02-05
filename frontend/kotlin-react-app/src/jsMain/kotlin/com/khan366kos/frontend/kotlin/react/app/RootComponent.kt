package com.khan366kos.frontend.kotlin.react.app

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable

class RootComponent(
    componentContext: ComponentContext,
) : ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    val childStack: Value<ChildStack<Config, Child>> = childStack(
        source = navigation,
        serializer = null, // Serialization not needed for JS target
        initialConfiguration = Config.Auth,
        handleBackButton = true,
        childFactory = ::createChild
    )

    private fun createChild(config: Config, context: ComponentContext): Child {
        return when (config) {
            is Config.Auth -> Child.Auth(
                component = AuthComponent(
                    componentContext = context,
                    apiClient = ApiClient,
                    onAuthSuccess = ::onAuthSuccess
                )
            )
            is Config.Workbook -> Child.Workbook(
                component = WorkbookComponent(
                    componentContext = context,
                    apiClient = ApiClient
                )
            )
        }
    }

    private fun onAuthSuccess() {
        navigation.push(Config.Workbook)
    }

    @Serializable
    sealed interface Config {
        @Serializable
        data object Auth : Config

        @Serializable
        data object Workbook : Config
    }

    sealed interface Child {
        data class Auth(val component: AuthComponent) : Child
        data class Workbook(val component: WorkbookComponent) : Child
    }
}