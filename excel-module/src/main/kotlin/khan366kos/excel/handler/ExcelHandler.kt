package khan366kos.excel.handler

import org.apache.poi.openxml4j.opc.OPCPackage
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.xssf.usermodel.XSSFWorkbook

class ExcelHandler {

    fun getAllSheetNames(filePath: String): List<String> {
        return OPCPackage.open(filePath).use { pkg ->
            val workbook = XSSFWorkbook(pkg)
            val sheetNames = mutableListOf<String>()
            for (i in 0 until workbook.numberOfSheets) {
                sheetNames.add(workbook.getSheetAt(i).sheetName)
            }
            sheetNames
        }
    }

    fun getColumnData(filePath: String, columnIndex: Int, sheetIndex: Int = 0): List<String> {
        return OPCPackage.open(filePath).use { pkg ->
            val workbook = XSSFWorkbook(pkg)
            val sheet = workbook.getSheetAt(sheetIndex)
            val columnData = mutableListOf<String>()

            for (rowIndex in 1 until sheet.physicalNumberOfRows) { // Skip header row (index 0)
                val row = sheet.getRow(rowIndex)
                if (row != null) {
                    val cell = row.getCell(columnIndex)
                    if (cell != null) {
                        // Convert cell to string regardless of its type
                        val cellValue = when (cell.cellType) {
                            org.apache.poi.ss.usermodel.CellType.STRING -> cell.stringCellValue
                            org.apache.poi.ss.usermodel.CellType.NUMERIC -> cell.numericCellValue.toString()
                            org.apache.poi.ss.usermodel.CellType.BOOLEAN -> cell.booleanCellValue.toString()
                            org.apache.poi.ss.usermodel.CellType.FORMULA -> cell.cellFormula
                            else -> ""
                        }
                        columnData.add(cellValue)
                    } else {
                        columnData.add("") // Add empty string if cell is null
                    }
                }
            }
            columnData
        }
    }

    fun read(filePath: String) {
        OPCPackage.open(filePath).use { pkg ->
            val workbook = XSSFWorkbook(pkg)
        }
    }
}