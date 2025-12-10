package com.khan366kos.elasticsearch.service

import co.elastic.clients.elasticsearch.ElasticsearchClient
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse
import com.khan366kos.common.models.Item
import org.apache.hc.core5.ssl.SSLContexts
import tools.jackson.module.kotlin.jacksonObjectMapper

class ElasticsearchService {
    private val serverUrl = "https://localhost:9200"
    private val mapper = jacksonObjectMapper()

    private val client: ElasticsearchClient = ElasticsearchClient.of { builder ->
        builder
            .host(serverUrl)
            .sslContext(
                SSLContexts.custom()
                    .loadTrustMaterial(null) { _, _ -> true }
                    .build()
            )
            .usernameAndPassword("elastic", "sIeIx_v0K5dTdOi4N*u4")
    }

    fun index(): CreateIndexResponse = client.indices().create { client ->
        client
            .index("items")
            .mappings { builder ->
                builder
                    .properties("itemId") { prop ->
                        prop.keyword { t -> t }
                    }
                    .properties("itemTitle") { prop ->
                        prop.text { text ->
                            text.analyzer("russian")
                        }
                    }
            }
    }

    fun delete() = client.indices().delete {builder ->
        builder
            .index("items")
    }

    fun indexProduct(product: Item) = client.index { index ->
        index
            .index("items")
            .id(product.itemId)
            .document(product)
    }

    fun document(id: String) = client.get({ builder ->
        builder
            .index("items")
            .id(id)
    }, Item::class.java)

    fun close() = client.close()
    fun products(searchText: String) = client.search({ search ->
        search
            .index("items")
            .query { value ->
                value
                    .match { builder ->
                        builder
                            .field("itemTitle")
                            .query(searchText)
                            .fuzziness("AUTO")

                    }
            }
    }, Item::class.java)
}