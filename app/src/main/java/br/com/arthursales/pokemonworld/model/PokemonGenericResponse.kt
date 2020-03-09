package br.com.arthursales.pokemonworld.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class PokemonGenericResponse (
    val name : String,
    val url : String
) : Parcelable