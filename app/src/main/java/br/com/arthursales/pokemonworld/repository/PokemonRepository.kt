package br.com.arthursales.pokemonworld.repository

import br.com.arthursales.pokemonworld.model.Pokemon
import br.com.arthursales.pokemonworld.model.PokemonDetails
import br.com.arthursales.pokemonworld.model.PokemonGenericResponse

interface PokemonRepository {

//    fun checkHealth(
//        onComplete:() -> Unit,
//        onError:(t: Throwable) -> Unit)

    fun getPokemons(
        onComplete:(List<PokemonGenericResponse>?) -> Unit,
        onError:(Throwable) -> Unit
    )

    fun getPokemons(
        sort: String,
        size: Int,
        onComplete:(List<Pokemon>) -> Unit,
        onError:(Throwable) -> Unit
    )

    fun getPokemonDetails(
        id: String,
        onComplete: (PokemonDetails?) -> Unit,
        onError: (Throwable?) -> Unit
    )

    fun updatePokemon(
        pokemon: Pokemon,
        onComplete:(Pokemon?) -> Unit,
        onError:(Throwable) -> Unit
    )
}