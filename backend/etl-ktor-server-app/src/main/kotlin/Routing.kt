package com.khan366kos.etl.ktor.server.app

import com.khan366kos.etl.assistant.transport.models.AuthorizationRequestTransport
import com.khan366kos.etl.excel.service.ManagedWorkbookResult
import com.khan366kos.etl.excel.service.dsl.function.useManagedWorkbook
import com.khan366kos.etl.mapper.toEtlWorkbookTransport
import com.khan366kos.etl.polynom.bff.createSimpleBffClient
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File
import java.nio.file.Files

fun Application.configureRouting() {
    routing {
        get("/sheets") {
            val managedWorkbook = useManagedWorkbook {
                path = "/Users/khan/Projects/etl-assistant/backend/etl-ktor-server-app/src/main/resources/Book.xlsx"
            } as ManagedWorkbookResult.Success

            call.respond(managedWorkbook.etlWorkbook.toEtlWorkbookTransport())
        }

        get("/storage-definitions") {
            try {
                val polynomClient = createSimpleBffClient()
                try {
                    val storageDefinitions = polynomClient.storageDefinitions()
                    call.respond(HttpStatusCode.OK, storageDefinitions)
                } finally {
                    polynomClient.close()
                }
            } catch (e: Exception) {
                call.respond(
                    HttpStatusCode.InternalServerError,
                    mapOf("error" to "Ошибка получения storage definitions: ${e.message}")
                )
            }
        }

        post("/authorize") {
            try {
                val authRequest = call.receive<AuthorizationRequestTransport>()

                // Log the received authorization data
                application.log.info("=== Authorization Request Received ===")
                application.log.info("Username: ${authRequest.username}")
                application.log.info("Password: ${authRequest.password}")
                application.log.info("Storage ID: ${authRequest.storageId}")
                application.log.info("======================================")

                call.respond(
                    HttpStatusCode.OK,
                    mapOf("message" to "Авторизация успешна", "storageId" to authRequest.storageId)
                )
            } catch (e: Exception) {
                application.log.error("Authorization error: ${e.message}", e)
                call.respond(
                    HttpStatusCode.InternalServerError,
                    mapOf("error" to "Ошибка авторизации: ${e.message}")
                )
            }
        }

        get("/references") {
            try {
                val polynomClient = createSimpleBffClient()
                try {
                    val references = polynomClient.getReference()
                    call.respond(HttpStatusCode.OK, references)
                } finally {
                    polynomClient.close()
                }
            } catch (e: Exception) {
                application.log.error("Error fetching references: ${e.message}", e)
                call.respond(
                    HttpStatusCode.InternalServerError,
                    mapOf("error" to "Ошибка получения справочников: ${e.message}")
                )
            }
        }

        post("/upload") {
            val multipartData = call.receiveMultipart()
            var fileName: String? = null
            var tempFile: File? = null

            try {
                multipartData.forEachPart { part ->
                    when (part) {
                        is PartData.FileItem -> {
                            fileName = part.originalFileName ?: "uploaded.xlsx"

                            tempFile = Files.createTempFile("upload_", "_${fileName}").toFile()

                            @Suppress("DEPRECATION")
                            part.streamProvider().use { input ->
                                tempFile!!.writeBytes(input.readBytes())
                            }
                        }
                        else -> {}
                    }
                    part.dispose()
                }

                if (tempFile == null) {
                    call.respond(HttpStatusCode.BadRequest, mapOf("error" to "Файл не найден"))
                    return@post
                }

                val result = useManagedWorkbook {
                    path = tempFile!!.absolutePath
                }

                when (result) {
                    is ManagedWorkbookResult.Success -> {
                        call.respond(result.etlWorkbook.toEtlWorkbookTransport())
                    }
                    is ManagedWorkbookResult.Failure -> {
                        call.respond(
                            HttpStatusCode.UnprocessableEntity,
                            mapOf("error" to "Ошибка обработки файла: ${result.exception.message}")
                        )
                    }
                }
            } catch (e: Exception) {
                call.respond(
                    HttpStatusCode.InternalServerError,
                    mapOf("error" to "Ошибка сервера: ${e.message}")
                )
            } finally {
                tempFile?.delete()
            }
        }

        get("/") {
            call.respondText("Hello World!")
        }
    }
}
