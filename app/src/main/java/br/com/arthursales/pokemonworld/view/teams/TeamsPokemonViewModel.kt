package br.com.arthursales.pokemonworld.view.teams

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.arthursales.pokemonworld.model.PokemonTeams
import br.com.arthursales.pokemonworld.view.listFavoritePokemon.ListFavoritePokemonViewModel

class TeamsPokemonViewModel : ListFavoritePokemonViewModel() {

//    val messageError: MutableLiveData<String> = MutableLiveData()
//    private val listPokemonTeams: MutableLiveData<List<PokemonTeams>> = MutableLiveData()

    val isLoadingTeams = MutableLiveData<Boolean>()

    val listTeams = MutableLiveData<MutableList<PokemonTeams>>()
    private val listPokemonTeams = mutableListOf<PokemonTeams>()


    fun getPokemonTeams(id: String) {
//        isLoadingTeams.value = true
        listPokemonTeams.add(PokemonTeams("gen1","Pokemon Yellow","Pokemon Yellow","Yellow"))
        listPokemonTeams.add(PokemonTeams("gen4","Pokemon Diamond","Pokemon Diamond","Blue"))
        listPokemonTeams.add(PokemonTeams("gen4","Pokemon Platinum","Pokemon Platinum","Grey"))
        listPokemonTeams.add(PokemonTeams("gen4","Pokemon HeartGold","Pokemon HeartGold","Gold"))
        listPokemonTeams.add(PokemonTeams("gen5","Pokemon Black","Pokemon Black","Black"))
        listTeams.value = listPokemonTeams

//        productRepository.getMyProduct(id,
//            {
//                pokemonTeams.value = it
//                messageError.value = ""
//                isLoading.value = false
//            }, {
//                pokemonTeams.value = emptyList()
//                messageError.value = it?.message
//                isLoading.value = false
//            }
//        )

    }
    // TODO: Implement the ViewModel with the data of teams
}
