package com.example.api_mvvm.data

import com.squareup.moshi.Json

data class Card(

    val id: String,
    val name: String,
    @Json(name = "image_url") val imgUrl: String

    )
