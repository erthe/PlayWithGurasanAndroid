package com.aochdjp.playwithgura_san.model.repositories

import com.aochdjp.playwithgura_san.model.Ramen
import com.aochdjp.playwithgura_san.model.repositories.abstracts.ApiFactory
import io.reactivex.Observable
import retrofit2.http.GET

val ramenApi: RamenApi
    get() = ApiFactory.retrofit!!.create(RamenApi::class.java)

interface RamenApi {
    @GET("api/ramen/")
    fun ramen(): Observable<Ramen>
}