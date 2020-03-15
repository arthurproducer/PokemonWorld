package br.com.arthursales.pokemonworld.repository

import br.com.arthursales.pokemonworld.model.UserDataResponse
import br.com.arthursales.pokemonworld.model.UserResponse

interface UserRepository {

//    fun checkHealth(
//        onComplete:() -> Unit,
//        onError:(t: Throwable) -> Unit
//    )

    fun getAllUser(
        onComplete: (List<UserResponse>?) -> Unit,
        onError: (t: Throwable) -> Unit
    )
}