package com.arthursales.smogon.repository

import android.util.Log
import com.arthursales.smogon.api.SmogonService
import com.arthursales.smogon.models.SmogonResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SmogonRepositoryImpl (
    private val smogonService: SmogonService
) : SmogonRepository {

    override fun getSmogonData(
        pokeName : String,
        onComplete: (SmogonResponse?) -> Unit,
        onError: (t: Throwable) -> Unit) {
        Log.i("POKEMON_PATH_URL", pokeName)
            smogonService.getSmogonData(pokeName)
                .enqueue(object : Callback<SmogonResponse> {
                    override fun onFailure(call: Call<SmogonResponse>, t: Throwable) {
                        Log.i("POKEMON_FAILURE","$t")
                        onError(t)
                    }

                    override fun onResponse(
                        call: Call<SmogonResponse>,
                        response: Response<SmogonResponse>
                    ) {
                        if (response.isSuccessful) {
                            onComplete(response.body())
                            Log.i("POKEMON_RESPONSE","${response.body()}")
                        } else {
                            onError(Throwable("Não foi possível realizar a chamada da API da Smogon."))
                        }            }

                })
    }
}