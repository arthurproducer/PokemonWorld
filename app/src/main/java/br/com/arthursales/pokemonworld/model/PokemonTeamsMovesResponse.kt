package br.com.arthursales.pokemonworld.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokemonTeamsMovesResponse(
    @SerializedName("name")
    val name : String,
    @SerializedName("type")
    val type : String
) : Parcelable