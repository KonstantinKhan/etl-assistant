package com.khan366kos

import com.khan366kos.bff.PolynomClient
import com.khan366kos.etl.assistant.transport.models.ElementCatalogTransport
import com.khan366kos.etl.assistant.transport.models.IdentifiableObjectTransport
import jdk.jfr.internal.OldObjectSample.emit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlin.time.measureTime

@OptIn(ExperimentalCoroutinesApi::class)
fun main(): Unit = runBlocking {
    val groupId = 2

    measureTime {
        val client = PolynomClient()

        println("===СПРАВОЧНИКИ===")
        val references = client.getAll()
        references.forEach { reference ->
            println(reference.name)
        }
        println()

        println("===КАТАЛОГИ===")
        val catalogs: Map<String?, Array<ElementCatalogTransport>> = references
            .map { reference -> Triple(reference.name, reference.objectId, reference.typeId) }
            .asFlow()
            .flowOn(Dispatchers.IO)
            .flatMapMerge { pair ->
                flow {
                    val result = client.getByReference(IdentifiableObjectTransport(pair.second, pair.third))
                    emit(pair.first to result)
                }
            }
            .toList()
            .toMap()

        catalogs.forEach { entry ->
            println(entry.key)
            entry.value.forEach { catalog -> println("   - ${catalog.name}: ${catalog.objectId}") }
        }
        println()

        println("""===ГРУППЫ КАТАЛОГА "СТАДАРТНЫЕ ИЗДЕЛИЯ"===""")

        val catalog =
            catalogs["Стандартные изделия"]?.first { it.name?.lowercase()?.contains("стандартные изделия") == true }
        if (catalog != null) {
            client.getByCatalog(IdentifiableObjectTransport(catalog.objectId, catalog.typeId)).forEach {
                println("  - ${it.name}")
            }
        }


    }.let {
        println("Время выполнения составило: $it")
    }
}