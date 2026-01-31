package khan366kos.excel.models

import khan366kos.excel.models.simple.Header
import khan366kos.excel.models.simple.Title

data class EtlSheet(
    val title: Title,
    val headers: List<Header>
)