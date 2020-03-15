package br.com.arthursales.pokemonworld.di

import android.content.Context
import br.com.arthursales.pokemonworld.api.PokemonService
import br.com.arthursales.pokemonworld.api.UserService
import br.com.arthursales.pokemonworld.api.interceptor.AuthInterceptor
import br.com.arthursales.pokemonworld.repository.PokemonRepository
import br.com.arthursales.pokemonworld.repository.PokemonRepositoryImpl
import br.com.arthursales.pokemonworld.repository.UserRepository
import br.com.arthursales.pokemonworld.repository.UserRepositoryImpl
import br.com.arthursales.pokemonworld.view.details.DetailPokemonViewModel
import br.com.arthursales.pokemonworld.view.listFavoritePokemon.ListFavoritePokemonViewModel
import br.com.arthursales.pokemonworld.view.listpokemon.ListPokemonsViewModel
import br.com.arthursales.pokemonworld.view.splash.SplashViewModel
import br.com.arthursales.pokemonworld.util.Constants
import br.com.arthursales.pokemonworld.view.login.LoginViewModel
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val networkModule = module {
    single<Interceptor> { AuthInterceptor() }
    single { createOkhttpClientAuth(get()) }
    single { createNetworkClient(get(),get(named("pokemonURL"))).create(PokemonService::class.java) }
    single { createNetworkClient(get(),get(named("userURL"))).create(UserService::class.java) }

    single { createPicassoAuth(get(), get()) }

    single(named("pokemonURL")) { Constants.pokemonURL }
    single(named("userURL")) { Constants.userURL }

}

val repositoryModule = module {
    single<PokemonRepository> { PokemonRepositoryImpl(get()) }
    single<UserRepository> {UserRepositoryImpl(get())}
}

val viewModelModule = module {
    viewModel { SplashViewModel(get(),get()) }
    viewModel { DetailPokemonViewModel(get()) }
    viewModel { ListPokemonsViewModel(get()) }
    viewModel { ListFavoritePokemonViewModel() }
    viewModel {LoginViewModel(get())}
}


private fun createNetworkClient(okHttpClient: OkHttpClient,baseUrl : String): Retrofit {
    return Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

private fun createOkhttpClientAuth(authInterceptor: Interceptor): OkHttpClient {
    val builder = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .addNetworkInterceptor(StethoInterceptor())
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
    return builder.build()
}

private fun createPicassoAuth(context: Context, okHttpClient: OkHttpClient): Picasso {
    return Picasso
        .Builder(context)
        .downloader(OkHttp3Downloader(okHttpClient))
        .build()
}