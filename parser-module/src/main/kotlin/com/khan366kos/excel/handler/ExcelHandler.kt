package com.khan366kos.excel.handler

import com.khan366kos.models.Item
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.FileInputStream

class ExcelHandler {
    val rowHandler = RowHandler()
    fun read(filePath: String) {
        val items = mutableListOf<Item>()
        FileInputStream(filePath).use { inputStream ->
            val workbook = XSSFWorkbook(inputStream)
            val sheet: Sheet = workbook.getSheet("Вспомы")
            for (row in sheet) {
                rowHandler.Item(row).takeIf { it != Item.NONE }?.let { items.add(it) }
            }
            workbook.close()
        }
        println(items.size)
    }
}