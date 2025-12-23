package khan366kos.excel.handler

import org.apache.poi.xssf.usermodel.XSSFSheet

class SheetBuilder {
    lateinit var sheet: XSSFSheet

    fun build(): Sheet = Sheet(sheet)
}