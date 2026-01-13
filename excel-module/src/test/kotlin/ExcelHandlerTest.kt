import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldNotBe
import khan366kos.excel.handler.ExcelHandler
import khan366kos.excel.handler.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook

class ExcelHandlerTest : ShouldSpec({
    lateinit var workbook: XSSFWorkbook

    beforeTest {
        workbook = Workbook.getInstance("C:\\Users\\han\\Desktop\\Классификатор.xlsx")
    }

    should("Excel workbook has worksheets") {
        val handler = ExcelHandler()
        val sheets = handler.sheets(workbook)

        sheets shouldNotBe null
    }

    afterTest {
        workbook.close()
    }
})