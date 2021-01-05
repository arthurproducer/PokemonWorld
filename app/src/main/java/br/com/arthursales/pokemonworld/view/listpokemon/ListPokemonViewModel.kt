package br.com.arthursales.pokemonworld.view.listpokemon

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.arthursales.pokemonworld.model.PokemonGenericResponse
import br.com.arthursales.pokemonworld.repository.PokemonRepository

class ListPokemonsViewModel(val pokemonRepository: PokemonRepository) : ViewModel(){

    val isLoading = MutableLiveData<Boolean>()
    val listPokemon = MutableLiveData<List<PokemonGenericResponse>>()
    val messageError = MutableLiveData<String>()
    private val _url = MutableLiveData<String>()
    val url: LiveData<String>
        get() = _url

    private val _next = MutableLiveData<String>()
    val next: LiveData<String>
        get() = _next

    private val _previous = MutableLiveData<String>()
    val previous: LiveData<String>
        get() = _previous

    fun getPokemons(offset: Int?, limit: Int?) {
        isLoading.value = true
        pokemonRepository.getAllPokemon(
            offset,
            limit,
            onLimits = {
                _next.value = it?.next_100
                _previous.value = it?.previous_100
            },
            onComplete = {
                isLoading.value = false
                listPokemon.value = it
                messageError.value = ""
            },
            onError = {
                isLoading.value = false
                listPokemon.value = listOf()
                messageError.value = it.message

            }
        )
    }
}