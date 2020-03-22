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
}