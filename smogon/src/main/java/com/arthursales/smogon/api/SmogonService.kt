package com.arthursales.smogon.api

import com.arthursales.smogon.models.SmogonResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SmogonService {

    @GET("/2020/11/gen4ou/1630/{pokeName}")
    fun getSmogonData(@Path("pokeName") pokeName: String) : Call<SmogonResponse>

    @GET("/{year}/{month}/gen4ou/1630/{pokeName}")
    fun getSmogonDataByMonthAndYear(
        @Path("pokeName") pokeName: String,
        @Path("month") month: Int,
        @Path("year") year: Int
    ) : Call<SmogonResponse>
}