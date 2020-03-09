package br.com.arthursales.pokemonworld.view.form

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.arthursales.pokemonworld.model.Pokemon
import br.com.arthursales.pokemonworld.model.PokemonDetails
import br.com.arthursales.pokemonworld.model.StatsResponse
import br.com.arthursales.pokemonworld.model.TypesResponse
import br.com.arthursales.pokemonworld.repository.PokemonRepository

class DetailPokemonViewModel(
    val pokemonRepository: PokemonRepository
) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val messageResponse = MutableLiveData<String>()
    val pokemonDetails = MutableLiveData<PokemonDetails>()
    val pokemonTypes = MutableLiveData<List<TypesResponse>>()
    val pokemonStats = MutableLiveData<List<StatsResponse>>()

    fun getPokemonDetails(id: String) {
        isLoading.value = true
        pokemonRepository.getPokemonDetails(id,
            {
                pokemonDetails.value = it
                pokemonTypes.value = it?.types
                pokemonStats.value = it?.stats
                messageResponse.value = ""
                isLoading.value = false
            }, {
                messageResponse.value = it?.message
                isLoading.value = false
            }
        )
    }

    fun updatePokemon(pokemon: Pokemon) {
        isLoading.value = true
        pokemonRepository.updatePokemon(
            pokemon = pokemon,
            onComplete = {
                isLoading.value = false
                messageResponse.value = "Dados atualizados com sucesso"
            },
            onError = {
                isLoading.value = false
                messageResponse.value = it.message
            }
        )
    }
}