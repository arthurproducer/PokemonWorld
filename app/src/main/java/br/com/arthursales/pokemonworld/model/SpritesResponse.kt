package br.com.arthursales.pokemonworld.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SpritesResponse (
    val back_default : String,
    val back_shiny : String,
    val front_default : String,
    val front_shiny : String
) : Parcelable