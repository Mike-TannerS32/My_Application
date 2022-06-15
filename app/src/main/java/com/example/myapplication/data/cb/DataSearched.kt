package com.example.myapplication.data.cb

import com.example.myapplication.data.model.Search

interface DataSearched {

    fun onDataSearchedSuccess(search: List<Search>)

    fun onDataSearchedFailed()
}