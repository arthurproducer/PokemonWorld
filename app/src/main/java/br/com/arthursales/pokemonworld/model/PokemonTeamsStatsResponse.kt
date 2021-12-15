package br.com.arthursales.pokemonworld.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokemonTeamsStatsResponse (
    @SerializedName("HP")
    var hp: Int,
    @SerializedName("Atk")
    var attack: Int,
    @SerializedName("Def")
    var defense: Int,
    @SerializedName("SpA")
    var spAttack: Int,
    @SerializedName("SpD")
    var spDefense: Int,
    @SerializedName("Spe")
    var speed: Int
) : Parcelable