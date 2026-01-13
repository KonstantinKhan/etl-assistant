package khan366kos.excel.handler

import kotlinx.coroutines.runBlocking
import org.apache.poi.openxml4j.opc.OPCPackage
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable
import org.apache.poi.xssf.eventusermodel.XSSFBReader
import org.apache.poi.xssf.eventusermodel.XSSFReader
import org.xml.sax.Attributes
import org.xml.sax.InputSource
import org.xml.sax.XMLReader
import org.xml.sax.helpers.DefaultHandler
import javax.xml.parsers.SAXParserFactory

fun main() = runBlocking {

    val filePaths = listOf(
        "C:\\Users\\han\\Desktop\\Данные с Турбины\\Данные с Турбины\\АСКОН\\Классификатор 29.10.2025.xlsx" to "Classifier",
        "C:\\Users\\han\\Desktop\\Данные с Турбины\\Данные с Турбины\\АСКОН\\Типы Windchill.xlsx" to "Winchill",
        "C:\\Users\\han\\Desktop\\Данные с Турбины\\Данные с Турбины\\АСКОН\\АСКОН. Выгрузка ТМ от 2025-10-29.xlsx" to "Technology"
    )

    filePaths.forEach { (filePath, name) ->
        println("$name workbook:")
        try {
            val count = countRowsInLargeExcel(filePath)
            println("in $name: $count строк")
        } catch (e: Exception) {
            println("  Error processing file: ${e.message}")
        }
        println()
    }
}

fun countRowsInLargeExcel(filePath: String): Long {
    var totalRows = 0L
    OPCPackage.open(filePath).use { pkg ->
        val reader = XSSFReader(pkg)
        val sheets = reader.sheetsData
        val saxParserFactory = SAXParserFactory.newInstance()
        saxParserFactory.isNamespaceAware = true

        while (sheets.hasNext()) {
            val sheetInputStream = sheets.next()

            val saxParser = saxParserFactory.newSAXParser()
            val xmlReader: XMLReader = saxParser.xmlReader

            val rowHandler = object : DefaultHandler() {
                override fun startElement(
                    uri: String,
                    localName: String,
                    qName: String,
                    attributes: Attributes
                ) {
                    if (qName == "row" || (localName == "row" && uri == "http://schemas.openxmlformats.org/spreadsheetml/2006/main")) {
                        totalRows++
                    }
                }
            }

            xmlReader.contentHandler = rowHandler

            val sheetSource = InputSource(sheetInputStream)
            xmlReader.parse(sheetSource)
            sheetInputStream.close()
        }
    }
    return totalRows
}