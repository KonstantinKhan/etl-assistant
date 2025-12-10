package com.khan366kos

import com.khan366kos.bff.PolynomClient
import com.khan366kos.bff.createSimpleBffClient
import com.khan366kos.common.models.simple.ElementName
import com.khan366kos.common.models.simple.GroupId
import com.khan366kos.common.requests.CreateElementRequest
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val polynomClient: PolynomClient = createSimpleBffClient()

    try {
        val response = polynomClient.element(CreateElementRequest(
            groupId = GroupId(2866),
            name = ElementName("Test bolt")
        ))
        println("response: $response")
    } catch (e: Exception) {
        println("Error calling API: ${e.message}")
    } finally {
        polynomClient.close()
    }
}