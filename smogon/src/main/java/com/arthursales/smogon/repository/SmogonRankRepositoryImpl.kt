package com.arthursales.smogon.repository

import android.content.Context
import com.arthursales.smogon.api.SmogonRankService
import com.arthursales.smogon.models.SmogonRankDataResponse
import com.arthursales.smogon.models.SmogonRankPokemonResponse
import com.arthursales.smogon.models.SmogonResponse
import com.google.gson.Gson
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
            .enqueue(object : Callback<SmogonRankPokemonResponse> {
                override fun onResponse(
                    call: Call<SmogonRankPokemonResponse>,
                    response: Response<SmogonRankPokemonResponse>
                ) = if (response.isSuccessful) {
                    val jsonString = context.assets.open("rank_gen1ou2021_02.json").bufferedReader().use { it.readText() }
                    val gson = Gson()
                    val data = gson.fromJson(jsonString, SmogonRankDataResponse::class.java)

                    onComplete(data.data)

                } else {
                    onError(Throwable("Não foi possível realizar a requisição"))
                }

                override fun onFailure(call: Call<SmogonRankPokemonResponse>, p1: Throwable) {
                    onError(p1)
                }

            })
    }
}