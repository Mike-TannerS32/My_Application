package com.example.myapplication.data.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface Dao {

    @Query("SELECT * FROM Dog ORDER BY name ASC")
    fun getAlphabetizedDogs(): LiveData<List<Model>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(dog: Model)

    @Query("DELETE FROM dog")
    fun deleteAll()

}