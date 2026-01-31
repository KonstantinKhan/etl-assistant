package com.khan366kos.frontend.kotlin.react.app

import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import react.useEffect
import react.useMemo
import react.useState

@Suppress("UNUSED_LAMBDA_EXPRESSION")
fun <T> useCollectState(flow: StateFlow<T>): T {

    val (state, setState) = useState(flow.value)

    val scope = useMemo() {
        MainScope()
    }

    useEffect(flow) {
        val job = flow.onEach { setState(it) }.launchIn(scope)
        ;
        {
            job.cancel()
            scope.cancel()
        }
    }

    return state
}