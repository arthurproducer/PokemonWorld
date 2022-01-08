package com.arthursales.smogon.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SmogonResponse (
    val tier : String,
    val pokemon : String,
    val rank : Int,
    @SerializedName("usage_pct")
    val usage : String,
    val dex : Int
) : Parcelable