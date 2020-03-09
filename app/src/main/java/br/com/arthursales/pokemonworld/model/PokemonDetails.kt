package br.com.arthursales.pokemonworld.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokemonDetails(
    val id: String,
    val name: String,
    val abilities : List<AbilitiesResponse>,
    val sprites : SpritesResponse,
    val stats : List<StatsResponse>,
    val types : List<TypesResponse>
) : Parcelable