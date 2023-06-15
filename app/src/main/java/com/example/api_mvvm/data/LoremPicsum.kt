package com.example.api_mvvm.data

import com.squareup.moshi.Json

data class LoremPicsum(

    val id: String,
    val author: String,
    @Json(name = "download_url") val url: String,

    )
