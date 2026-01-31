package khan366kos.excel.mapper

import khan366kos.excel.models.EtlSheet
import khan366kos.excel.models.EtlWorkbook
import khan366kos.excel.models.simple.Header
import khan366kos.excel.models.simple.Title
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook

fun Sheet.toEtl(): EtlSheet = EtlSheet(
    title = Title(sheetName),
    headers = first().toList().map { cell ->
        Header(cell.stringCellValue)
    }
)

fun XSSFWorkbook.toEtl(): EtlWorkbook = EtlWorkbook(
    sheets = (0 until numberOfSheets).map { index ->
        getSheetAt(index).toEtl()
    }
)