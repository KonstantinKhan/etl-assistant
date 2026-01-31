package khan366kos.excel.dsl.builders

import khan366kos.excel.ManagedWorkbook
import khan366kos.excel.ManagedWorkbookResult
import khan366kos.excel.dsl.function.ManagedWorkbookDsl
import khan366kos.excel.types.ManagedWorkbookAction
import kotlin.use

@ManagedWorkbookDsl
class ManagedWorkbookBuilder {
    var path: String? = null
    private val actions: MutableList<ManagedWorkbookAction> = mutableListOf()

    internal fun action(block: ManagedWorkbookAction) {
        actions.add(block)
    }

    suspend fun build(): ManagedWorkbookResult {
        val path = path ?: return ManagedWorkbookResult.Failure(IllegalArgumentException("Path must be specified"))

        return when (val result = ManagedWorkbook.open(path)) {
            is ManagedWorkbookResult.Success -> {
                result.workbook.use { workbook ->
                    actions.forEach { action ->
                        workbook.action()
                    }
                }
                result
            }

            is ManagedWorkbookResult.Failure -> {
                result
            }
        }
    }
}