package br.com.arthursales.pokemonworld.api

import br.com.arthursales.pokemonworld.model.*
import retrofit2.Call
import retrofit2.http.*

interface PokemonService {

//    @GET("/api/v2")
//    fun checkHealth() : Call<HealthResponse>

    @GET("/api/pokemon")
    fun getPokemons(
        @Query("sort") sort: String,
        @Query("size") size: Int
    ) : Call<PokemonResponseOld>

    @GET("/api/v2/pokemon?limit=100")
    fun getPokemons() : Call<PokemonDataResponse>

    @GET("/api/v2/pokemon/{id}")
    fun getPokemonDetails(@Path("id") id: String) : Call<PokemonDetails>

    @PUT("/api/pokemon")
    fun updatePokemon(@Body pokemon: Pokemon
    ) : Call<Pokemon>
}