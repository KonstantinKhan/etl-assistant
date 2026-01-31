package com.khan366kos.frontend.kotlin.react.app

import kotlinx.browser.document
import react.create
import react.dom.client.createRoot
import web.dom.Element

fun main() {
    val container = document.getElementById("root") ?: error("Could not find #root element in HTML")
    createRoot(container.unsafeCast<Element>()).render(App.create())
}