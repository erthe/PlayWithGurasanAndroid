package com.aochdjp.playwithgura_san.model.repositories

import com.aochdjp.playwithgura_san.model.Log
import io.reactivex.Observable
import retrofit2.http.GET

interface LogApi {
    @GET("api/uo_aoe_log/")
    fun log(): Observable<Log>
}