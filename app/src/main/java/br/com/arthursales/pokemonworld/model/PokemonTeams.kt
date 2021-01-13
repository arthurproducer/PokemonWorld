package br.com.arthursales.pokemonworld.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokemonTeams(
    val name: String,
    val tier: String,
    //var game: String,
    //var color: String,
    val pokemons : List<PokemonTeamsDetails>
) : Parcelable