package com.aochdjp.playwithgura_san.model

data class Menu(
    val title: String = "",
    val return_status: Short = 404,
    val menu_version: Float = 0.1f,
    val menus: List<MenuList>
) {
    data class MenuList(
        val url: String = "",
        val title: String = ""
    )
}