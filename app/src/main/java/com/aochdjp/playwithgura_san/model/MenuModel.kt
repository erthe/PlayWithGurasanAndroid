package com.aochdjp.playwithgura_san.model

import com.google.gson.annotations.SerializedName

data class Menu(
    @SerializedName("title")
    val title: String = "",
    @SerializedName("return_status")
    val return_status: Short = 404,
    @SerializedName("menu_version")
    val menu_version: Float = 0.1f,
    val menu: List<Menus>
) {
    data class Menus(
        @SerializedName("url")
        val url: String = "",
        @SerializedName("title")
        val titles: String = ""
    )
}