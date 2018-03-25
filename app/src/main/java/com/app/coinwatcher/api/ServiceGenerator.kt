package com.pandorika.cryptomap.api

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory

import java.util.TimeZone
import java.util.concurrent.TimeUnit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

/**
 * Created by Leonov Oleg, http://pandorika-it.com on 24.05.16.
 */
object ServiceGenerator {
    private val BASE_URL = "https://api.coinmarketcap.com/v1/"
    private val httpClient = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(0, TimeUnit.SECONDS)

    val retrofit = Retrofit.Builder()
            .client(httpClient.build())
            .baseUrl(BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create(
                    ObjectMapper().registerModule(SimpleModule())
                            .setTimeZone(TimeZone.getDefault())
            ))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()!!

    /*** API для работы с сервером  */
    val serverApi: ServerApi
        get() = retrofit.create(ServerApi::class.java)
}
