package com.example.myapplication.data.cb

import com.example.myapplication.data.model.Breed

interface DataRetriever{
    fun onDataFetchSuccess(breeds: List<Breed>)
    fun onDataFetchedFailed()
}