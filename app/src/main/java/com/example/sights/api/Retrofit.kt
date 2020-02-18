package com.example.sights.api

import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun getRetrofit(): Retrofit {

    val httpClientBuilder = OkHttpClient.Builder()
    httpClientBuilder.addInterceptor { chain ->
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.header("Content-Type", "application/json")
        requestBuilder.header("Accept", "application/json")
        chain.proceed(requestBuilder.build())
    }

    val httpClient = httpClientBuilder.build()

    return Retrofit.Builder()
            .baseUrl("https://tartu.kalm.ee/api.php/")
            .client(httpClient)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()

}


