package com.example.myapplication

import android.app.Application
import com.example.myapplication.data.persistence.DaoRepository
import com.example.myapplication.data.persistence.KennelDatabase

class Application : Application(){

    val database by lazy { KennelDatabase.getDatabase(this) }
    val repository by lazy{DaoRepository(database.dogDao())}

}