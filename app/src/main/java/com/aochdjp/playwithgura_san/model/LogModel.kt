package com.aochdjp.playwithgura_san.model

data class Log(
    val title: String = "",
    val return_status: Short = 404,
    val date: String = "",
    val file_name: String = "",
    val lines: List<LogList>
) {
    data class LogList(
        val line: String = ""
    )
}