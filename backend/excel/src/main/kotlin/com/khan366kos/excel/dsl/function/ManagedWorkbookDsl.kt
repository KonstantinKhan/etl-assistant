package com.khan366kos.excel.dsl.function

import com.khan366kos.excel.ManagedWorkbookResult
import com.khan366kos.excel.dsl.builders.ManagedWorkbookBuilder

@DslMarker
annotation class ManagedWorkbookDsl
suspend fun useManagedWorkbook(block: ManagedWorkbookBuilder.() -> Unit): ManagedWorkbookResult =
    ManagedWorkbookBuilder().apply(block).build()