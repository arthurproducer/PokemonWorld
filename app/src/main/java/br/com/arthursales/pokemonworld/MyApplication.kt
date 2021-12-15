package br.com.arthursales.pokemonworld

import android.app.Application
import br.com.arthursales.pokemonworld.di.networkModule
import br.com.arthursales.pokemonworld.di.repositoryModule
import br.com.arthursales.pokemonworld.di.viewModelModule
import com.arthursales.smogon.SmogonInitializer
import com.arthursales.smogon.SmogonKoinComponent
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this)

        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }

}