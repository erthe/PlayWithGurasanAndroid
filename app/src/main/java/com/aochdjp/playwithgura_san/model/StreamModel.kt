package com.aochdjp.playwithgura_san.model

data class Stream(
    val title: String = "",
    val return_status: Short = 404,
    val twitch: List<StreamList>,
    val youtube: List<StreamList>,
    val cavetube: List<StreamList>,
    val nico: List<StreamList>
) {
    data class StreamList(
        val title: String = "",
        val name: String = "",
        val url: String = "",
        val viewer: String = "",
        val stream_id: String = ""
    )
}