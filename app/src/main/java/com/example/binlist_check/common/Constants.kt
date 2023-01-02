package com.example.binlist_check.common

object Constants {
    const val BASE_URL = "https://lookup.binlist.net/"

    const val DATABASE_NAME = "past_queries_database"
    const val TABLE_NAME = "past_queries_table"

    fun httpExceptionMessage(code: Int): String {
        return "Http error: $code"
    }
    const val loadCardDataIOExceptionMessage = "IO exception: check Internet connection!"
    const val wrongInputMessage = "Wrong input! Bin should ne a number"
    const val loadQueriesIOExceptionMessage = "IO exception: failed to load database!"
    const val activityNotFoundExceptionMessage = "No application to perform this action is installed!"

}