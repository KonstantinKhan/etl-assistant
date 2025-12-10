package com.khan366kos.excel.handler

import com.khan366kos.common.models.Item
import org.apache.poi.ss.usermodel.Row

class RowHandler() {
    private fun valid(row: Row, column: Int = 1): Boolean {
        val regex = Regex("""(.+?ГОСТ\s*[^/]+)/\s*(.+?ГОСТ\s*.+)""")
        val matchResult = regex.find(row.getCell(column).stringCellValue)
        return matchResult == null
    }

    fun Item(row: Row): Item {
        return if (valid(row)) {
            Item(
                row.getCell(2).stringCellValue,
                row.getCell(1).stringCellValue,
                row.getCell(1).stringCellValue
            )
        } else {
            Item.NONE
        }
    }
}