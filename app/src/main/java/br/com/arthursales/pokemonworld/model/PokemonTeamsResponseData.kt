package br.com.arthursales.pokemonworld.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokemonTeamsResponseData(
    @SerializedName("Alpha Sapphire")
    val gamesListAlphaSapphire : List<PokemonTeams>
) : Parcelable