package com.example.carownersfilter.remote

import com.example.carownersfilter.model.Filters
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface CarOwnersFilterAPI {

    @GET("accounts")
    fun getFiltersAsync():Deferred<List<Filters>>
}