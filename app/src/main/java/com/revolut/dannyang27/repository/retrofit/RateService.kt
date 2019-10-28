package com.revolut.dannyang27.repository.retrofit

import com.revolut.dannyang27.model.restmodel.Currency
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RateService{
    @GET("/latest")
    suspend fun getLatestRate(@Query("base") base: String) : Response<Currency>
}