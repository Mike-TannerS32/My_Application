package com.example.myapplication.data.persistence

import androidx.lifecycle.LiveData

class DaoRepository(private val Dao: Dao) {

    val allDogs : LiveData<List<Model>> = Dao.getAlphabetizedDogs()

    fun insert(dog : Model){
        KennelDatabase
            .databaseWriteExecutor
            .execute {
                Dao.insert(dog)
            }
    }
}