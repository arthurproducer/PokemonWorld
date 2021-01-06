package br.com.arthursales.pokemonworld.view.teams

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.arthursales.pokemonworld.model.PokemonTeams

class TeamsPokemonViewModel : ViewModel() {

    val messageError: MutableLiveData<String> = MutableLiveData()
    val pokemonTeams: MutableLiveData<List<PokemonTeams>> = MutableLiveData()

    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun getPokemonTeams(id: String) {
        isLoading.value = true
        productRepository.getMyProduct(id,
            {
                pokemonTeams.value = it
                messageError.value = ""
                isLoading.value = false
            }, {
                lendingProducts.value = emptyList()
                messageError.value = it?.message
                isLoading.value = false
            }
        )
    }
    // TODO: Implement the ViewModel with the data of teams
}
