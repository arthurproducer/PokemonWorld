package com.arthursales.smogon.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SmogonRankPokemonResponse (
    val pokemon : SmogonRankDataResponse
) : Parcelable