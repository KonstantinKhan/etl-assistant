package khan366kos.excel.handler

fun workbook(block: WorkbookBuilder.() -> Unit): Workbook = WorkbookBuilder().apply(block).build()
fun sheet(block: SheetBuilder.() -> Unit): Sheet = SheetBuilder().apply(block).build()