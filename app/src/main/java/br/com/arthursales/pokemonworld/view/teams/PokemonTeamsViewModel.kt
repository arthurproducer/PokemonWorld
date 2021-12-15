package br.com.arthursales.pokemonworld.view.teams

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.arthursales.pokemonworld.model.PokemonTeams
import br.com.arthursales.pokemonworld.repository.PokemonTeamsRepository

class PokemonTeamsViewModel(private val pokemonTeamsRepository: PokemonTeamsRepository) :
    ViewModel() {
    val isLoadingTeams = MutableLiveData<Boolean>()

    val listTeams = MutableLiveData<MutableList<PokemonTeams>>()
    private val listPokemonTeams = mutableListOf<PokemonTeams>()



    fun getPokemonTeams() {
//        isLoadingTeams.value = true
        listPokemonTeams.addAll(pokemonTeamsRepository.getAllPokemonTeams())
        listTeams.value = listPokemonTeams

        }
//        isLoadingTeams.value = false
}
