package com.example.api_mvvm.network

import com.example.api_mvvm.data.LoremPicsum
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://picsum.photos/v2/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)// Passa a url base da API para o retrofit
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

interface LoremPicsumApiService {
    @GET("list") // Faz o fetch na api
    suspend fun getImages(): List<LoremPicsum> // Get images irá retornar uma lista de imagens
}

object LoremPicsumApi {
    val retrofitService: LoremPicsumApiService by lazy {
        retrofit.create(LoremPicsumApiService::class.java)
    }
}