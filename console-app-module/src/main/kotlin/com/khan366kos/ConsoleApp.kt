package com.khan366kos

import com.khan366kos.bff.PolynomClient
import com.khan366kos.bff.createSimpleBffClient
import com.khan366kos.common.models.business.Identifier
import com.khan366kos.common.models.simple.ObjectId
import com.khan366kos.common.models.simple.TypeId
import com.khan366kos.common.requests.PropertyOwnerRequest
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val polynomClient: PolynomClient = createSimpleBffClient()

    try {
        val response = polynomClient.propertyOwner(
            PropertyOwnerRequest(
                identifier = Identifier(
                    objectId = ObjectId(474100),
                    typeId = TypeId(4)
                )
            )
        )
        println("response: $response")
    } catch (e: Exception) {
        println("Error calling API: ${e.message}")
    } finally {
        polynomClient.close()
    }
}