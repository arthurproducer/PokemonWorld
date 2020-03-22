package br.com.arthursales.pokemonworld.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StatsResponse (
    var base_stat: Int,
    var effort: Int,
    var stat: PokemonGenericResponse
) : Parcelable {
    constructor() :this(
        0,
        0,
        PokemonGenericResponse("","")
    )
}
