package com.example.myapplication.data

import android.util.Log
import com.example.myapplication.data.cb.DataRetriever
import com.example.myapplication.data.model.Breed
import retrofit2.*
import retrofit2.converter.moshi.MoshiConverterFactory

private const val Tag = "DogsAPIClient"

object DogsAPIClient {
    private val apiDog by lazy{
        setup()
    }

    private fun setup(): DogAPI{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }
    fun getListOfBreeds(listener: DataRetriever){
        apiDog.getBreedsList().enqueue(object : Callback<List<Breed>>{
            override fun onResponse(call: Call<List<Breed>>, response: Response<List<Breed>>) {
                Log.d(Tag, "onResponse: ${response.body()}")
                if(response.isSuccessful){
                listener.onDataFetchSuccess(response.body()!!)
                }
            }

            override fun onFailure(call: Call<List<Breed>>, t: Throwable) {
                Log.d(Tag, "onFailure: ${t.message}")
                listener.onDataFetchedFailed()
            }
        })
    }

}