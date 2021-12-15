package br.com.arthursales.pokemonworld.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokemonTeamsDetails(
    @SerializedName("pokemon")
    val name: String,
    @SerializedName("number")
    val number: Int,
    @SerializedName("item")
    val item: String,
    @SerializedName("OT")
    val firstOwner: String,
    @SerializedName("ID no.")
    val idFirstOwner: String,
    @SerializedName("shiny")
    val shiny: Boolean,
    @SerializedName("ability")
    val ability: String,
    @SerializedName("level")
    val level: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("evs")
    val evs : List<PokemonTeamsStatsResponse>?,
    @SerializedName("ivs")
    val ivs : List<PokemonTeamsStatsResponse>?,
    val nature : String,
    val moves : List<PokemonTeamsMovesResponse>?,
    @SerializedName("status")
    val status : List<PokemonTeamsStatsResponse>?,
    @SerializedName("first date")
    val firstDate : String
) : Parcelable