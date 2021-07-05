package br.com.arthursales.pokemonworld.repository

import br.com.arthursales.pokemonworld.api.PokemonService
import br.com.arthursales.pokemonworld.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonRepositoryImpl(
    private val pokemonService: PokemonService
) : PokemonRepository {

    override fun getPokemonDetails(
        pokemonId: String,
        onComplete: (PokemonDetails?) -> Unit,
        onError: (Throwable?) -> Unit
    ) {
        pokemonService.getPokemonDetails(pokemonId)
            .enqueue(object : Callback<PokemonDetails> {
                override fun onFailure(call: Call<PokemonDetails>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(
                    call: Call<PokemonDetails>,
                    response: Response<PokemonDetails>
                ) {
                    if (response.isSuccessful) {
                        onComplete(response.body())
                    } else {
                        onError(Throwable("Não foi possível realizar a chamada."))
                    }
                }
            })
    }

    override fun getAllPokemon(
        offset: Int?,
        limit: Int?,
        onLimits: (PokemonResponseData?) -> Unit,
        onComplete: (List<PokemonGenericResponse>?) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        pokemonService.getAllPokemon(offset, limit)
            .enqueue(object : Callback<PokemonResponseData> {
                override fun onFailure(call: Call<PokemonResponseData>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(
                    call: Call<PokemonResponseData>,
                    response: Response<PokemonResponseData>
                ) {
                    if (response.isSuccessful) {
                        onLimits(response.body())
                        onComplete(response.body()?.pokemons ?: listOf())
                    } else {
                        onError(Throwable("Não foi possível realizar a requisição"))
                    }
                }
            })
    }

    override fun updatePokemon(
        pokemon: Pokemon,
        onComplete: (Pokemon?) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        pokemonService
            .updatePokemon(pokemon)
            .enqueue(object : Callback<Pokemon> {
                override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                    if (response.isSuccessful) {
                        onComplete(response.body())
                    } else {
                        onError(Throwable("Não foi possível realizar a requisição"))
                    }
                }
            })
    }

    override fun checkHealth(
        onComplete: () -> Unit,
        onError: (t: Throwable) -> Unit
    ) {
        pokemonService
            .checkHealth()
            .enqueue(object : Callback<HealthResponse> {
                override fun onFailure(call: Call<HealthResponse>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(
                    call: Call<HealthResponse>,
                    response: Response<HealthResponse>
                ) {
                    if (response.isSuccessful) {
                        onComplete()
                    } else {
                        onError(Throwable("Não foi possível realizar a requisição"))
                    }
                }
            })

    }
}