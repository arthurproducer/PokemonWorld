package com.arthursales.smogon.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SmogonResponse (
    val tier : String,
    val pokemon : String,
    val rank : Int,
    val usage : String
) : Parcelable