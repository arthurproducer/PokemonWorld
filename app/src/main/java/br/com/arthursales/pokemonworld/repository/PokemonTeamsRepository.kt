package br.com.arthursales.pokemonworld.repository

import br.com.arthursales.pokemonworld.model.PokemonTeams


interface PokemonTeamsRepository {

    fun getPokemonTeams(
    ) : List<PokemonTeams>

}