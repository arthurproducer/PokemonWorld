package br.com.arthursales.pokemonworld.api

import br.com.arthursales.pokemonworld.model.*
import retrofit2.Call
import retrofit2.http.*

interface PokemonService {

    @GET("/api/v2/pokemon?limit=100")
    fun getPokemons() : Call<PokemonDataResponse>

    @GET("/api/v2/pokemon/{id}")
    fun getPokemonDetails(@Path("id") id: String) : Call<PokemonDetails>
}