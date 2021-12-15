package com.arthursales.smogon

import android.app.Application
import com.arthursales.smogon.di.networkModule
import com.arthursales.smogon.di.repositoryModule
import com.arthursales.smogon.di.viewModelModule
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {

    //TODO tratar corretamente o Koin ao usar lib

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