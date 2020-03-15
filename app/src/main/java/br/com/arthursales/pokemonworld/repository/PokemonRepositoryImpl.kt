package br.com.arthursales.pokemonworld.repository

import br.com.arthursales.pokemonworld.api.PokemonService
import br.com.arthursales.pokemonworld.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonRepositoryImpl(
    val pokemonService: PokemonService
) : PokemonRepository {

    override fun getPokemonDetails(
        id: String,
        onComplete: (PokemonDetails?) -> Unit,
        onError: (Throwable?) -> Unit
    ) {
        pokemonService.getPokemonDetails(id)
            .enqueue(object: Callback<PokemonDetails>{
                override fun onFailure(call: Call<PokemonDetails>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(call: Call<PokemonDetails>, response: Response<PokemonDetails>) {
                    if(response.isSuccessful){
                        onComplete(response.body())
                    } else{
                        onError(Throwable("Não foi possível realizar a chamada."))
                    }
                }
            })
    }

    override fun getPokemons(
        onComplete: (List<PokemonGenericResponse>?) -> Unit,
        onError: (Throwable) -> Unit) {
        pokemonService.getPokemons()
            .enqueue(object : Callback<PokemonDataResponse> {
                override fun onFailure(call: Call<PokemonDataResponse>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(call: Call<PokemonDataResponse>, response: Response<PokemonDataResponse>) {
                    if (response.isSuccessful) {
                        onComplete(response.body()?.pokemons ?: listOf())
                    } else {
                        onError(Throwable("Não foi possível realizar a requisição"))
                    }
                }
            })
    }

    override fun updatePokemon(pokemon: Pokemon, onComplete: (Pokemon?) -> Unit, onError: (Throwable) -> Unit) {
        pokemonService
            .updatePokemon(pokemon)
            .enqueue(object : Callback<Pokemon>{
                override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                    if(response.isSuccessful) {
                        onComplete(response.body())
                    } else {
                        onError(Throwable("Não foi possível realizar a requisição"))
                    }
                }
            })
    }

    override fun getPokemons(
        sort: String,
        size: Int,
        onComplete: (List<Pokemon>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        pokemonService
            .getPokemons(sort, size)
            .enqueue(object : Callback<PokemonResponseOld> {
                override fun onFailure(call: Call<PokemonResponseOld>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(call: Call<PokemonResponseOld>, responseOld: Response<PokemonResponseOld>) {
                    if (responseOld.isSuccessful) {
                        onComplete(responseOld.body()?.pokemons ?: listOf())
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

                override fun onResponse(call: Call<HealthResponse>, response: Response<HealthResponse>) {
                    if (response.isSuccessful) {
                        onComplete()
                    } else {
                        onError(Throwable("Não foi possível realizar a requisição"))
                    }
                }
            })

    }
}