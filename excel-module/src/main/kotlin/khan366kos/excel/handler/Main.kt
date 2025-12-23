package khan366kos.excel.handler

fun main() {
    val workbook = workbook {
        path = """/home/khan/Загрузки/Классификатор.xlsx"""
        sheets()
    }
    println(workbook)

    workbook.sheets.forEach { sheet -> println(sheet.sheet.sheetName) }
}