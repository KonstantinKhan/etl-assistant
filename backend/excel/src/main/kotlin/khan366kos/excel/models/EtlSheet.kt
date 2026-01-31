package khan366kos.excel.models

import khan366kos.excel.models.simple.EtlTableHeader
import khan366kos.excel.models.simple.EtlSheetTitle

data class EtlSheet(
    val title: EtlSheetTitle,
    val headers: List<EtlTableHeader>,
    val entriesSize: Int
)