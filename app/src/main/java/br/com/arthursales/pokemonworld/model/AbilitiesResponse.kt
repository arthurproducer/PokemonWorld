package br.com.arthursales.pokemonworld.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class AbilitiesResponse (
    val ability : PokemonGenericResponse,
    val is_hidden : Boolean,
    val slot : Int
) : Parcelable