package com.arthursales.smogon

import android.app.Application
import android.content.Context
import android.content.Intent
import com.arthursales.smogon.di.networkModule
import com.arthursales.smogon.di.repositoryModule
import com.arthursales.smogon.di.viewModelModule
import com.arthursales.smogon.view.rankTier.RankTierActivity
import org.koin.android.ext.koin.androidContext
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin
import org.koin.dsl.koinApplication

object SmogonInitializer {
    private lateinit var application: Application
    internal lateinit var koinApplication: KoinApplication


    fun application(application: Application) = apply { SmogonInitializer.application = application }

    val koin: Koin by lazy {
        koinApplication {
            androidContext(application)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }.koin
    }

    fun build() = apply {
        koinApplication = koinApplication {
            androidContext(application)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }

    fun start() {
        application.startActivity(
            Intent(application, RankTierActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
        )
    }
}

interface SmogonKoinComponent : KoinComponent {
    override fun getKoin(): Koin = SmogonInitializer.koin //TODO verificar se não é melhor o GlobalContext.get().koin
}

object MyKoinContext {
    var koinApp : KoinApplication? = null
}