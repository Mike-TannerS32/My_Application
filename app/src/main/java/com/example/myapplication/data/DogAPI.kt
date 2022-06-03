package com.example.myapplication.data

import com.example.myapplication.data.model.Breed
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface DogAPI {

    @Headers("x-api-key: $API_KEY")
    @GET(BREEDS)
    fun getBreedsList(): Call<List<Breed>>
}