package br.com.arthursales.pokemonworld.api

import br.com.arthursales.pokemonworld.model.UserDataResponse
import retrofit2.Call
import retrofit2.http.GET

interface UserService{

    @GET("/api/users?page=1")
    fun getAllUsers() : Call<UserDataResponse>

}