package br.com.arthursales.pokemonworld.api

import br.com.arthursales.pokemonworld.model.*
import retrofit2.Call
import retrofit2.http.*

interface PokemonService {

    @GET("/api/v2")
    fun checkHealth() : Call<HealthResponse>

    @GET("/api/v2/pokemon")
    fun getAllPokemon(
        @Query("offset") offset: Int?,
        @Query("limit") limit: Int?) : Call<PokemonResponseData>

    @GET("/api/v2/pokemon/{id}")
    fun getPokemonDetails(@Path("id") id: String) : Call<PokemonDetails>

    @PUT("/api/pokemon")
    fun updatePokemon(@Body pokemon: Pokemon
    ) : Call<Pokemon>
}