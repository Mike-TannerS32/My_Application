package com.example.myapplication.data.persistence

import androidx.lifecycle.LiveData

class DaoRepository(private val Dao: Dao) {

    val allDogs : LiveData<List<Model>> = Dao.getAlphabetizedDogs()

    fun deleteById(id: String, onLoaded: (Unit) -> Unit) {
        KennelDatabase
            .databaseWriteExecutor
            .execute {
                Dao.deleteById(id)
                onLoaded(Unit)
            }
    }

    fun getDogs(onLoaded: (List<Model>) -> Unit) {
        KennelDatabase
            .databaseWriteExecutor
            .execute {
                onLoaded(Dao.getDogs())
            }
    }
    fun insert(dog : Model){
        KennelDatabase
            .databaseWriteExecutor
            .execute {
                Dao.insert(dog)
            }
    }
}