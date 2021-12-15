package br.com.arthursales.pokemonworld.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeamsResponse(
    @SerializedName("Teams")
    val teams : ArrayList<PokemonTeams>
) : Parcelable