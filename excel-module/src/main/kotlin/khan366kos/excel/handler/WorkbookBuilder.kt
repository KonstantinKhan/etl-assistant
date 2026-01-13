package khan366kos.excel.handler

import org.apache.poi.openxml4j.opc.OPCPackage
import org.apache.poi.xssf.usermodel.XSSFWorkbook

class WorkbookBuilder {
    var path: String = ""
    var sheets: MutableList<Sheet> = mutableListOf()

    fun sheets(block: ArrayList<Sheet>.() -> Unit) {
        sheets.addAll(ArrayList<Sheet>().apply(block))
    }

    fun sheets(): Unit =
        OPCPackage.open(path).use { pkg ->
            with(XSSFWorkbook(pkg)) {
                (0 until numberOfSheets).forEach { sheetNumber ->
                    sheets.add(
                        sheet {
                            sheet = getSheetAt(sheetNumber)
                        }
                    )
                }
            }
        }


    fun build(): Workbook = Workbook(
        path,
        sheets
    )
}