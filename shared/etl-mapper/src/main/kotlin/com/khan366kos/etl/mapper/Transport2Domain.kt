package com.khan366kos.etl.mapper

import com.khan366kos.common.excel.models.EtlSheet
import com.khan366kos.common.excel.models.EtlWorkbook
import com.khan366kos.common.excel.models.simple.EtlSheetTitle
import com.khan366kos.common.excel.models.simple.EtlTableHeader
import com.khan366kos.etl.assistant.transport.models.EtlSheetTransport
import com.khan366kos.etl.assistant.transport.models.EtlWorkbookTransport

fun EtlWorkbookTransport.toEtlWorkbook(): EtlWorkbook {
    return EtlWorkbook(
        sheets = this.sheets.map { it.toEtlSheet() }
    )
}

fun EtlSheetTransport.toEtlSheet(): EtlSheet {
    return EtlSheet(
        title = EtlSheetTitle(this.title),
        headers = this.headers.map { EtlTableHeader(it) },
        entriesSize = this.entriesSize
    )
}