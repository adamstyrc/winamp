package com.adamstyrc.api

import com.adamstyrc.api.response.ITunesSongsResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ITunesApi {

    @GET("search")
    fun getSearch(@Query("term") term: String)
            : Observable<Response<ITunesSongsResponse>>
}
