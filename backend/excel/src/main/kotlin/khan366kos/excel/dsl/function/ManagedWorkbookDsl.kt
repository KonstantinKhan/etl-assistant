package khan366kos.excel.dsl.function

import khan366kos.excel.ManagedWorkbookResult
import khan366kos.excel.dsl.builders.ManagedWorkbookBuilder

@DslMarker
annotation class ManagedWorkbookDsl
fun useManagedWorkbook(block: ManagedWorkbookBuilder.() -> Unit): ManagedWorkbookResult =
    ManagedWorkbookBuilder().apply(block).build()