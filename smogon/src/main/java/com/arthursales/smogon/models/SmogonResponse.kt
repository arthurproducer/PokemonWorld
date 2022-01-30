package com.arthursales.smogon.models

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SmogonResponse(
    val tier: String,
    val pokemon: String,
    val rank: Int,
    val usage_pct: Double,
    val dex: Int
) : Parcelable {
    constructor() : this(
        "",
    "",
    0,
    0.0,
    0
    ) {}
}