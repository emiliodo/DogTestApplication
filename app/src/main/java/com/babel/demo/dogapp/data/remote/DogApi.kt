package com.babel.demo.dogapp.data.remote

import com.babel.demo.dogapp.data.model.DogResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface DogApi {
    @GET("images/search")
    fun getDogsAsync(
        @Query("limit") limit: Int,
        @Query("page") page: Int
    ): Deferred<List<DogResponse>>
}