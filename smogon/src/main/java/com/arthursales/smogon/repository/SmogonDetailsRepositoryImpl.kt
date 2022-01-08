package com.arthursales.smogon.repository

import com.arthursales.smogon.api.SmogonDetailsService
import com.arthursales.smogon.models.SmogonResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SmogonDetailsRepositoryImpl(
    private val smogonDetailsService: SmogonDetailsService
) : SmogonDetailsRepository {

    override fun getSmogonData(
        pokeName: String,
        onComplete: (SmogonResponse?) -> Unit,
        onError: (t: Throwable) -> Unit
    ) {
        smogonDetailsService.getSmogonData(pokeName)
            .enqueue(object : Callback<SmogonResponse> {
                override fun onFailure(call: Call<SmogonResponse>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(
                    call: Call<SmogonResponse>,
                    response: Response<SmogonResponse>
                ) {
                    if (response.isSuccessful) {
                        onComplete(response.body())
                    } else {
                        onError(Throwable("Não foi possível realizar a chamada da API da Smogon."))
                    }
                }

            })
    }

    override fun getSmogonDataByMonthAndYear(
        pokeName: String,
        month: Int,
        year: Int,
        onComplete: (SmogonResponse?) -> Unit,
        onError: (t: Throwable) -> Unit
    ) {
        smogonDetailsService.getSmogonDataByMonthAndYear(pokeName,month,year)
            .enqueue(object : Callback<SmogonResponse> {
                override fun onFailure(call: Call<SmogonResponse>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(
                    call: Call<SmogonResponse>,
                    response: Response<SmogonResponse>
                ) {
                    if (response.isSuccessful) {
                        onComplete(response.body())
                    } else {
                        onError(Throwable("Não foi possível realizar a chamada da API da Smogon."))
                    }
                }

            })
    }
}