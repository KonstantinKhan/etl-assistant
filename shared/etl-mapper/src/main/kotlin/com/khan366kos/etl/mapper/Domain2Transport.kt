package com.khan366kos.etl.mapper

import com.khan366kos.common.excel.models.EtlSheet
import com.khan366kos.common.excel.models.EtlWorkbook
import com.khan366kos.etl.assistant.transport.models.EtlSheetTransport
import com.khan366kos.etl.assistant.transport.models.EtlWorkbookTransport

fun EtlWorkbook.toEtlWorkbookTransport(): EtlWorkbookTransport {
    return EtlWorkbookTransport(
        sheets = this.sheets.map { it.toEtlSheetTransport() }
    )
}

fun EtlSheet.toEtlSheetTransport(): EtlSheetTransport {
    return EtlSheetTransport(
        title = this.title.asString(),
        headers = this.headers.map { it.asString() },
        entriesSize = this.entriesSize
    )
}