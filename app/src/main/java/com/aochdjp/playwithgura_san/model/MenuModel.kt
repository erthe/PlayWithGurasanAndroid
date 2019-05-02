package com.aochdjp.playwithgura_san.model

import com.google.gson.annotations.SerializedName

data class Menu(
    @SerializedName("title")
    val title: String = "",
    @SerializedName("return_status")
    val return_status: Short = 404,
    @SerializedName("menu_version")
    val menu_version: Float = 0.1f,
    val menu: List<Contents>
) {
    data class Contents(
        @SerializedName("urls")
        val urls: MutableList<String>,
        @SerializedName("titles")
        val titles: MutableList<String>
    )
}