package br.com.arthursales.pokemonworld.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class PokemonGenericResponse (
    var name : String?,
    val url : String
) : Parcelable