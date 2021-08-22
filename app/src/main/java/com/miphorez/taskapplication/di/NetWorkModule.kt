package com.miphorez.taskapplication.di

import com.miphorez.taskapplication.network.Api
import com.miphorez.taskapplication.network.WeatherRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


fun createRetrofitClient() =
    retrofitClient(baseUrl, okHttpClient())

val networkModule = module {
    single { createRetrofitClient().create(Api::class.java) }
    single { WeatherRepository(get()) }
}

private const val baseUrl = "http://api.openweathermap.org/"

private fun okHttpClient() =
    OkHttpClient.Builder().run {
            addInterceptor(HttpLoggingInterceptor().apply{
                readTimeout(60L, TimeUnit.SECONDS)
                connectTimeout(60L, TimeUnit.SECONDS)
                writeTimeout(60L, TimeUnit.SECONDS)
            })
        build()
    }

@Suppress("SameParameterValue")
private fun retrofitClient(baseUrl: String, httpClient: OkHttpClient): Retrofit =
    Retrofit.Builder().run {
        baseUrl(baseUrl)
        client(httpClient)
        addConverterFactory(GsonConverterFactory.create())
        build()
    }