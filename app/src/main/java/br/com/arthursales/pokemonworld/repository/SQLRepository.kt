package br.com.arthursales.pokemonworld.repository

import br.com.arthursales.pokemonworld.model.PokemonDetails


interface SQLRepository {

    fun insert(
        pokemonId : Int,
        onComplete:() -> Unit,
        onError:(t: Throwable) -> Unit
    )
}