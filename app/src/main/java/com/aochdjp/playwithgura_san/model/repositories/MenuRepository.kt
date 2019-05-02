package com.aochdjp.playwithgura_san.model.repositories

import com.aochdjp.playwithgura_san.model.Menu
import io.reactivex.Observable
import retrofit2.http.GET

interface MenuApi {
    @GET("api/menu/")
    fun menu(): Observable<Menu>
}