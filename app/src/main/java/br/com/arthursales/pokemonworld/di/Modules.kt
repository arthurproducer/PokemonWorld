package br.com.arthursales.pokemonworld.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
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
import br.com.arthursales.pokemonworld.util.Constants.POKEMON_URL
import br.com.arthursales.pokemonworld.util.Constants.USER_URL
import br.com.arthursales.pokemonworld.util.Keys.POKEMON_URL_KEY
import br.com.arthursales.pokemonworld.util.Keys.USER_URL_KEY
import br.com.arthursales.pokemonworld.util.Keys.PREFERENCES_USER_ID
import br.com.arthursales.pokemonworld.view.login.LoginViewModel
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val networkModule = module {
    single<Interceptor> { AuthInterceptor() }
    single { createOkhttpClientAuth(get()) }
    single { createNetworkClient(get(),get(named(POKEMON_URL_KEY))).create(PokemonService::class.java) }
    single { createNetworkClient(get(),get(named(USER_URL_KEY))).create(UserService::class.java) }

    single { createPicassoAuth(get(), get()) }

    single(named(POKEMON_URL_KEY)) { POKEMON_URL }
    single(named(USER_URL_KEY)) { USER_URL }

}

val repositoryModule = module {
    single<PokemonRepository> { PokemonRepositoryImpl(get()) }
    single<UserRepository> {UserRepositoryImpl(get())}
}

val viewModelModule = module {
    viewModel { DetailPokemonViewModel(get(),get()) }
    viewModel { ListPokemonsViewModel(get()) }
    viewModel { ListFavoritePokemonViewModel() }
    viewModel {LoginViewModel(get())}
}

val sharedPreferencesModule = module {
    single {
        provideSettingsPreferences(androidApplication())
    }
}

private fun provideSettingsPreferences(app: Application): SharedPreferences =
    app.getSharedPreferences(PREFERENCES_USER_ID, Context.MODE_PRIVATE)

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