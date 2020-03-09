package br.com.arthursales.pokemonworld.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class PokemonResponseData(
    @SerializedName("count")
    val total_number : String,
    @SerializedName("next")
    val next_100 : String,
    @SerializedName("previous")
    val previous_100 : String?,
    @SerializedName("results")
    val pokemons : List<PokemonGenericResponse>
) : Parcelable