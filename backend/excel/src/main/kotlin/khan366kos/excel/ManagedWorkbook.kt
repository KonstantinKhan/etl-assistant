package khan366kos.excel

import khan366kos.excel.mapper.toEtl
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Paths

sealed class ManagedWorkbookResult {
    data class Success(internal val workbook: ManagedWorkbook) : ManagedWorkbookResult()
    data class Failure(val exception: Exception) : ManagedWorkbookResult()
}

class ManagedWorkbook private constructor(
    private val workbook: XSSFWorkbook,
    private val inputStream: InputStream,
) : AutoCloseable {

    override fun close() {
        inputStream.use {
            workbook.close()
        }
    }

    internal fun sheets(): List<Sheet> = (0 until workbook.numberOfSheets).map { sheetNumber ->
        workbook.getSheetAt(sheetNumber)
    }

    internal suspend fun etlSheets() = sheets().map { sheet -> sheet.toEtl() }

    internal fun workbook(): XSSFWorkbook = workbook

    companion object {
        internal fun open(path: String): ManagedWorkbookResult {
            val inputStream: InputStream = Files.newInputStream(Paths.get(path))
            return try {
                ManagedWorkbookResult.Success(ManagedWorkbook(XSSFWorkbook(inputStream), inputStream))
            } catch (e: Exception) {
                inputStream.close()
                ManagedWorkbookResult.Failure(e)
            }
        }
    }
}