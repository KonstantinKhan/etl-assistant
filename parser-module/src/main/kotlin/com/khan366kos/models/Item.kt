package com.khan366kos.models

data class Item(
    val itemId: String,
    val itemTitle: String,
    val specification: String
) {
    constructor() : this("", "", "")

    companion object {
        val NONE = Item()
    }
}