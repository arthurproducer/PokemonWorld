package br.com.arthursales.pokemonworld.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokemonTeams(
    val tier: String,
    val name: String,
    var game: String,
    var color: String
) : Parcelable