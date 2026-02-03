package com.khan366kos.frontend.kotlin.react.app

import com.khan366kos.etl.assistant.transport.models.EtlWorkbookTransport
import com.khan366kos.etl.assistant.transport.models.StorageDefinitionTransport
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import web.file.File as BrowserFile
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

object ApiClient {
    private const val DEFAULT_BASE_URL = "http://localhost:8080"

    private val baseUrl: String
        get() {
            val apiUrl = js("window.API_BASE_URL")
            return (apiUrl as? String) ?: DEFAULT_BASE_URL
        }

    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun fetchSheets(): EtlWorkbookTransport {
        return httpClient.get("$baseUrl/sheets").body()
    }

    suspend fun fetchStorageDefinitions(): List<StorageDefinitionTransport> {
        return httpClient.get("$baseUrl/storage-definitions").body()
    }

    suspend fun uploadFile(file: BrowserFile): EtlWorkbookTransport {
        return httpClient.submitFormWithBinaryData(
            url = "$baseUrl/upload",
            formData = formData {
                append("file", file.asByteArray(), Headers.build {
                    append(HttpHeaders.ContentDisposition, "filename=\"${file.name}\"")
                })
            }
        ).body()
    }

    fun close() {
        httpClient.close()
    }
}

private suspend fun BrowserFile.asByteArray(): ByteArray = suspendCoroutine { cont ->
    val reader: dynamic = js("new FileReader()")
    reader.onload = { event: dynamic ->
        val result: ByteArray = js("new Int8Array(event.target.result)").unsafeCast<ByteArray>()
        cont.resume(result)
    }
    reader.readAsArrayBuffer(this)
}
