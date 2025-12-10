package com.khan366kos

import com.khan366kos.excel.handler.ExcelHandler

fun main(args: Array<String>) {
//    val service = ElasticsearchService()
//
//    try {
//        service.delete()
//    } catch (e: Exception) {
//        println("Индекс не существовал или не может быть удален: ${e.message}")
//    }
//
//    service.index()
//
//    service.indexProduct(
//        Item(
//            itemId = "0001",
//            itemTitle = "Герметик Виксинт У-1-18 кремнийорганический двухкомпонентный, отверждающийся без нагревания МРТУ 38-3-406-69"
//        )
//    )
//
//    service.indexProduct(
//        Item(
//            itemId = "0001",
//            itemTitle = "Герметик Виксинт У-2-28 кремнийорганический многокомпонентный, отверждающийся без нагревания МРТУ 38-3-408-69"
//        )
//    )
//
//    Thread.sleep(1000)
//
//    val result = service.document("0001")
//    println("result: $result")
//    val results = service.products("Герметик \"Виксинт У-2-28 (НТ)\" ТУ 38.303-04-04-90")
//    println("results: $results")
//    service.close()

    val excelHandler = ExcelHandler()
    excelHandler.read("elements_simple.xlsx")
}