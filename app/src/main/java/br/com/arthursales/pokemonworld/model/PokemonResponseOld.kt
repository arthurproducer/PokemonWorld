package br.com.arthursales.pokemonworld.model

import com.google.gson.annotations.SerializedName

data class PokemonResponseOld(
    @SerializedName("content")val pokemons: List<Pokemon>
)