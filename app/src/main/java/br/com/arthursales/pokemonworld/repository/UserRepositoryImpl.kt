package br.com.arthursales.pokemonworld.repository

import br.com.arthursales.pokemonworld.api.UserService
import br.com.arthursales.pokemonworld.model.HealthResponse
import br.com.arthursales.pokemonworld.model.UserDataResponse
import br.com.arthursales.pokemonworld.model.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepositoryImpl (val userService: UserService): UserRepository{
    override fun getAllUser(
        onComplete: (List<UserResponse>?) -> Unit,
        onError: (t: Throwable) -> Unit) {
        userService.getAllUsers()
            .enqueue(object : Callback<UserDataResponse>{
                override fun onFailure(call: Call<UserDataResponse>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(call: Call<UserDataResponse>, response: Response<UserDataResponse>) {
                    if (response.isSuccessful) {
                        onComplete(response.body()?.data)
                    } else {
                        onError(Throwable("Não foi possível realizar a requisição"))
                    }
                }
            })
    }

    override fun checkHealth(
        onComplete: () -> Unit,
        onError: (t: Throwable) -> Unit
    ) {
        userService.checkHealth()
            .enqueue(object : Callback<HealthResponse> {
                override fun onFailure(call: Call<HealthResponse>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(call: Call<HealthResponse>, response: Response<HealthResponse>) {
                    if (response.isSuccessful) {
                        onComplete()
                    } else {
                        onError(Throwable("Não foi possível realizar a requisição"))
                    }
                }
            })

    }


}