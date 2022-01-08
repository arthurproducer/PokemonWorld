package com.arthursales.smogon.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SmogonRankDataResponse (
    val data : List<SmogonResponse>
) : Parcelable