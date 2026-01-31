package khan366kos.excel.mapper

import khan366kos.excel.entriesSize
import khan366kos.excel.headers
import khan366kos.excel.models.EtlSheet
import khan366kos.excel.models.EtlWorkbook
import khan366kos.excel.models.simple.EtlTableHeader
import khan366kos.excel.models.simple.EtlSheetTitle
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook

suspend fun Sheet.toEtl(): EtlSheet = EtlSheet(
    title = EtlSheetTitle(sheetName),
    headers = headers().map { header ->
        EtlTableHeader(header)
    },
    entriesSize = entriesSize()
)

suspend fun XSSFWorkbook.toEtl(): EtlWorkbook = EtlWorkbook(
    sheets = (0 until numberOfSheets).map { index ->
        getSheetAt(index).toEtl()
    }
)