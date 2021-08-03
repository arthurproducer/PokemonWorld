package com.arthursales.smogon.di

import android.content.Context
import com.arthursales.smogon.api.SmogonService
import com.arthursales.smogon.api.interceptor.AuthInterceptor
import com.arthursales.smogon.repository.SmogonRepository
import com.arthursales.smogon.repository.SmogonRepositoryImpl
import com.arthursales.smogon.view.rankTier.RankTierViewModel
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val networkModule = module {
    single<Interceptor> { AuthInterceptor() }
    single { createOkhttpClient(get()) }
    single { createNetworkClient(get()).create(SmogonService::class.java) }
    single { createPicassoAuth(get(), get()) }
}

val repositoryModule = module {
    single<SmogonRepository> { SmogonRepositoryImpl(get()) }
}

val viewModelModule = module {
    viewModel { RankTierViewModel(get()) }

}

private fun createNetworkClient(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://smogon-usage-stats.herokuapp.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

private fun createOkhttpClient(authInterceptor: Interceptor): OkHttpClient {
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