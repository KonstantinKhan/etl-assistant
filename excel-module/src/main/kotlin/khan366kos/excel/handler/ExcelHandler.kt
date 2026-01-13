package khan366kos.excel.handler

import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook

class ExcelHandler() {

    fun sheets(workbook: Workbook): List<Sheet> = workbook.toList()

    fun countRowsSafe(sheet: Sheet): Int {
        return sheet.physicalNumberOfRows
    }

    fun countTotalRowsWithLastRow(sheet: Sheet): Int {
        val lastRowNum = sheet.lastRowNum

        return lastRowNum + 1
    }
}