package com.aochdjp.playwithgura_san.model.repositories

import com.aochdjp.playwithgura_san.model.Log
import com.aochdjp.playwithgura_san.model.repositories.abstracts.ApiFactory
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

val logApi: LogApi
    get() = ApiFactory.retrofit!!.create(LogApi::class.java)

interface LogApi {
    @GET("api/uo_aoe_log/")
    fun log(@Query("date") date: String): Observable<Log>
}