package com.arthursales.smogon.repository

import com.arthursales.smogon.models.SmogonResponse

interface SmogonRepository {
     fun getSmogonData(
        pokeName: String,
        onComplete: (SmogonResponse?) -> Unit,
        onError: (t: Throwable) -> Unit
    )
}