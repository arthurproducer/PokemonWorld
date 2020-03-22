package br.com.arthursales.pokemonworld.repository

import br.com.arthursales.pokemonworld.model.PokemonDetails
import br.com.arthursales.pokemonworld.model.PokemonGenericResponse

interface PokemonRepository {

    fun getPokemons(
        onComplete:(List<PokemonGenericResponse>?) -> Unit,
        onError:(Throwable) -> Unit
    )

    fun getPokemonDetails(
        id: String,
        onComplete: (PokemonDetails?) -> Unit,
        onError: (Throwable?) -> Unit
    )
}