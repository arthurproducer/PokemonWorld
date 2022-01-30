package com.arthursales.smogon.api

import com.arthursales.smogon.models.SmogonRankDataResponse
import com.arthursales.smogon.models.SmogonRankPokemonResponse
import com.arthursales.smogon.models.SmogonResponse
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.GET

interface SmogonRankService {

    @GET("/data/2021-02/gen8ou")
    fun getSmogonRankData() : Call<JsonElement>

}