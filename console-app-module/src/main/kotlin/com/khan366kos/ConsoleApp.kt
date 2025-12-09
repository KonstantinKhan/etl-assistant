package com.khan366kos

import com.khan366kos.bff.PolynomClient
import com.khan366kos.bff.createSimpleBffClient
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A simple console application that demonstrates using the SimpleBffClient to call external APIs
 */
fun main() = runBlocking {
    val polynomClient: PolynomClient = createSimpleBffClient()

    try {
        // Example API call to a public API using the simple client
        val response: PostmanEchoGetResponse = polynomClient.get("https://postman-echo.com/get?foo1=bar1&foo2=bar2")

        println("Status: ${response.args}")
        println("Headers: ${response.headers}")
        println("URL: ${response.url}")
    } catch (e: Exception) {
        println("Error calling API: ${e.message}")
    } finally {
        polynomClient.close()
    }
}

@Serializable
data class PostmanEchoGetResponse(
    @SerialName("args")
    val args: Map<String, String>,
    
    @SerialName("headers")
    val headers: Map<String, String>,
    
    @SerialName("url")
    val url: String
)