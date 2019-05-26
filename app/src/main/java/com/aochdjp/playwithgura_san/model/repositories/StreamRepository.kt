package com.aochdjp.playwithgura_san.model.repositories

import com.aochdjp.playwithgura_san.model.Stream
import com.aochdjp.playwithgura_san.model.repositories.abstracts.ApiFactory
import io.reactivex.Observable
import retrofit2.http.GET

val streamApi: StreamApi
    get() = ApiFactory.retrofit!!.create(StreamApi::class.java)

interface StreamApi {
    @GET("api/stream/")
    fun stream(): Observable<Stream>
}