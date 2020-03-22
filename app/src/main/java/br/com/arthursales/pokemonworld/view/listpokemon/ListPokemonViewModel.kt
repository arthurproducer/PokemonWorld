package br.com.arthursales.pokemonworld.view.listpokemon

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.arthursales.pokemonworld.model.PokemonGenericResponse
import br.com.arthursales.pokemonworld.repository.PokemonRepository

class ListPokemonsViewModel(private val pokemonRepository: PokemonRepository) : ViewModel(){

    val isLoading = MutableLiveData<Boolean>()
    val pokemons = MutableLiveData<List<PokemonGenericResponse>>()
    val messageError = MutableLiveData<String>()

    fun getPokemons() {
        isLoading.value = true
        pokemonRepository.getPokemons(
            onComplete = {
                isLoading.value = false
                pokemons.value = it
                messageError.value = ""
            },
            onError = {
                isLoading.value = false
                pokemons.value = listOf()
                messageError.value = it.message

            }
        )
    }
}