package com.arthursales.smogon.repository

import com.arthursales.smogon.models.SmogonResponse

interface SmogonRankRepository {

    fun getSmogonRankData(
        onComplete: (List<SmogonResponse?>) -> Unit,
        onError: (t: Throwable) -> Unit
    )
}