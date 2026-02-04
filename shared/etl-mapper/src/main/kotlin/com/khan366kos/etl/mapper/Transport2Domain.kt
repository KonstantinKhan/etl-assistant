package com.khan366kos.etl.mapper

import com.khan366kos.common.excel.models.EtlSheet
import com.khan366kos.common.excel.models.EtlWorkbook
import com.khan366kos.common.excel.models.simple.EtlSheetTitle
import com.khan366kos.common.excel.models.simple.EtlTableHeader
import com.khan366kos.common.models.auth.AuthorizationCredentials
import com.khan366kos.common.models.definitions.StorageDefinition
import com.khan366kos.etl.assistant.transport.models.AuthorizationRequestTransport
import com.khan366kos.etl.assistant.transport.models.EtlSheetTransport
import com.khan366kos.etl.assistant.transport.models.EtlWorkbookTransport
import com.khan366kos.etl.assistant.transport.models.StorageDefinitionTransport

fun EtlWorkbookTransport.toEtlWorkbook(): EtlWorkbook =
    EtlWorkbook(
        sheets = this.sheets.map { it.toEtlSheet() }
    )

fun EtlSheetTransport.toEtlSheet(): EtlSheet =
    EtlSheet(
        title = EtlSheetTitle(this.title),
        headers = this.headers.map { EtlTableHeader(it) },
        entriesSize = this.entriesSize
    )

fun StorageDefinitionTransport.toStorageDefinition(): StorageDefinition =
    StorageDefinition(
        storageId = this.storageId,
        displayName = this.displayName
    )

fun AuthorizationRequestTransport.toAuthorizationCredentials(): AuthorizationCredentials =
    AuthorizationCredentials(
        username = this.username,
        password = this.password,
        storageId = this.storageId
    )