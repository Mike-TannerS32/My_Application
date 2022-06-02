package com.example.myapplication.data.cb

import com.example.myapplication.data.Breed

interface DataRetriever{
    fun onDataFetchSuccess(breeds: List<Breed>)
    fun onDataFetchedFailed()
}