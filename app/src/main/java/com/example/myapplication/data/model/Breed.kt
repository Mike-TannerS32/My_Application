package com.example.myapplication.data.model

import com.squareup.moshi.Json

data class Breed(
    val alt_names: String,
    val experimental: Int,
    val hairless: Int,
    val hypoallergenic: Int,
    val id: String,
    val life_span: String,
    val name: String,
    val natural: Int,
    val origin: String,
    val rare: Int,
    val reference_image_id: Any,
    val rex: Int,
    val short_legs: Int,
    val suppressed_tail: Int,
    val temperament: String,
    @field:Json(name = "weight")
    val weight: Weight,
    val wikipedia_url: String,
    @field:Json(name = "height")
    val height: Height
)