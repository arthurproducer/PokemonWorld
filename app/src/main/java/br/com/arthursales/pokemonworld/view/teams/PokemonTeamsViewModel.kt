package br.com.arthursales.pokemonworld.view.teams

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.arthursales.pokemonworld.model.PokemonTeams
import br.com.arthursales.pokemonworld.repository.PokemonTeamsRepository
import br.com.arthursales.pokemonworld.view.listFavoritePokemon.ListFavoritePokemonViewModel

class PokemonTeamsViewModel(private val pokemonTeamsRepository: PokemonTeamsRepository) :
    ViewModel() {

//    val messageError: MutableLiveData<String> = MutableLiveData()
//    private val listPokemonTeams: MutableLiveData<List<PokemonTeams>> = MutableLiveData()

    val isLoadingTeams = MutableLiveData<Boolean>()

    val listTeams = MutableLiveData<MutableList<PokemonTeams>>()
    private val listPokemonTeams = mutableListOf<PokemonTeams>()


    fun getPokemonTeams() {
//        isLoadingTeams.value = true
        //TODO Usar inputStream
//        listPokemonTeams.add(PokemonTeams("gen1","Pokemon Yellow","Pokemon Yellow","Yellow"))
//        listPokemonTeams.add(PokemonTeams("gen4","Pokemon Diamond","Pokemon Diamond","Blue"))
//        listPokemonTeams.add(PokemonTeams("gen4","Pokemon Platinum","Pokemon Platinum","Grey"))
//        listPokemonTeams.add(PokemonTeams("gen4","Pokemon HeartGold","Pokemon HeartGold","Gold"))
//        listPokemonTeams.add(PokemonTeams("gen5","Pokemon Black","Pokemon Black","Black"))
        listPokemonTeams.addAll(pokemonTeamsRepository.getPokemonTeams())
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
}
