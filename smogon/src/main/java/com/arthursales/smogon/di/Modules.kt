package com.arthursales.smogon.di

import android.content.Context
import com.arthursales.smogon.api.SmogonDetailsService
import com.arthursales.smogon.api.SmogonRankService
import com.arthursales.smogon.api.interceptor.AuthInterceptor
import com.arthursales.smogon.repository.SmogonDetailsRepository
import com.arthursales.smogon.repository.SmogonDetailsRepositoryImpl
import com.arthursales.smogon.repository.SmogonRankRepository
import com.arthursales.smogon.repository.SmogonRankRepositoryImpl
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
    single { createNetworkClient(get(),"https://smogon-usage-stats.herokuapp.com").create(SmogonDetailsService::class.java) }
    single { createNetworkClient(get(),"https://usage-server.herokuapp.com").create(SmogonRankService::class.java) }
    single { createPicassoAuth(get(), get()) }
}

val repositoryModule = module {
    single<SmogonDetailsRepository> { SmogonDetailsRepositoryImpl(get()) }
    single<SmogonRankRepository> { SmogonRankRepositoryImpl(get(),get()) }

}

val viewModelModule = module {
    viewModel { RankTierViewModel(get(),get()) }

}

private fun createNetworkClient(okHttpClient: OkHttpClient, baseUrl: String): Retrofit {
    return Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(baseUrl)
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