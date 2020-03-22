package br.com.arthursales.pokemonworld.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokemonDetails(
    var id: Int,
    var name: String,
    var abilities : List<AbilitiesResponse>?,
    var sprites : SpritesResponse,
    var stats : List<StatsResponse>?,
    var types : List<TypesResponse>?
) : Parcelable {
        constructor(id: Int, name: String, sprites: SpritesResponse): this(
            id,
            name,
            null,
            sprites,
            null,
            null
    )
}