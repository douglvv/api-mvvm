package com.example.api_mvvm.network

import com.example.api_mvvm.data.Card
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://db.ygoprodeck.com/api/v7"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

interface YugiOhApiService {
    @GET("cardinfo.php?type=Normal+Monster") // Faz o fetch na API
    suspend fun getCards(): List<Card>
}

object YugiOhApi{
    val retrofitService: YugiOhApiService by lazy {
        retrofit.create(YugiOhApiService::class.java)
    }
}