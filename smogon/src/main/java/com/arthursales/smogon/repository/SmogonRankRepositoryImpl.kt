package com.arthursales.smogon.repository

import android.content.Context
import com.arthursales.smogon.api.SmogonRankService
import com.arthursales.smogon.models.SmogonRankDataResponse
import com.arthursales.smogon.models.SmogonRankPokemonResponse
import com.arthursales.smogon.models.SmogonResponse
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SmogonRankRepositoryImpl(
    private val smogonRankService: SmogonRankService,
    private val context: Context
) : SmogonRankRepository {

    override fun getSmogonRankData(
        onComplete: (List<SmogonResponse?>) -> Unit,
        onError: (t: Throwable) -> Unit
    ) {
        smogonRankService.getSmogonRankData()
            .enqueue(object : Callback<JsonElement> {
                override fun onResponse(
                    call: Call<JsonElement>,
                    response: Response<JsonElement>
                ) = if (response.isSuccessful) {
//                    val jsonString = context.assets.open("rank_gen1ou2021_02.json").bufferedReader().use { it.readText() }
//                    val gson = Gson()
//                    val data = gson.fromJson(jsonString, SmogonRankDataResponse::class.java)
//
//                    onComplete(data.data)

                    val objectMapper = ObjectMapper()
                    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

                    val product: SmogonRankDataResponse = objectMapper.readValue(response.body().toString(), SmogonRankDataResponse::class.java)


                    val list: MutableList<SmogonResponse> = ArrayList<SmogonResponse>(product.data.values)
                    onComplete(list.sortedBy { it.rank })


                } else {
                    onError(Throwable("Não foi possível realizar a requisição"))
                }

                override fun onFailure(call: Call<JsonElement>, p1: Throwable) {
                    onError(p1)
                }

            })
    }
}