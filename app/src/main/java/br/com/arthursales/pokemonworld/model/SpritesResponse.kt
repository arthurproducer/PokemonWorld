package br.com.arthursales.pokemonworld.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SpritesResponse (
    var back_default : String?,
    var back_shiny : String?,
    var front_default : String?,
    var front_shiny : String?
) : Parcelable {
    constructor() :this(
        back_default = null,
        back_shiny = null,
        front_default = null,
        front_shiny =null
        )
}