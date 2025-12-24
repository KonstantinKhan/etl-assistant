package com.khan366kos

import com.khan366kos.bff.PolynomClient
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    val client = PolynomClient()
    println(client.storageDefinitions())
}