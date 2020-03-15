package br.com.arthursales.pokemonworld.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserResponse(
    val id : Int,
    val email : String,
    val first_name : String,
    val last_name : String,
    val avatar : String
): Parcelable