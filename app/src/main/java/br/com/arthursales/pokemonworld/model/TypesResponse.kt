package br.com.arthursales.pokemonworld.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class TypesResponse (
    var slot: Int,
    var type: PokemonGenericResponse
) : Parcelable
