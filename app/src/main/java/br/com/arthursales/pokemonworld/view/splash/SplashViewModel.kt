package br.com.arthursales.pokemonworld.view.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.arthursales.pokemonworld.repository.PokemonRepository
import br.com.arthursales.pokemonworld.repository.UserRepository

class SplashViewModel(
    private val pokemonRepository: PokemonRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    val messageError = MutableLiveData<String>()

//    fun checkHealthPokemon() {
//        pokemonRepository.checkHealth(
//            onComplete = {
//                messageError.value = ""
//            }, onError = {
//                messageError.value = it.message
//            })
//    }
//    fun checkHealthUser() {
//        userRepository.checkHealth(
//            onComplete = {
//                messageError.value = ""
//            }, onError = {
//                messageError.value = it.message
//            })
//    }
}