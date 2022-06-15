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

    @Query("SELECT * FROM Dog")
    fun getDogs(): List<Model>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(dog: Model)

    @Query("SELECT * FROM dog WHERE id=:id ")
    fun findDogById(id: String): LiveData<Model>

    @Query("DELETE FROM Dog WHERE id = :id")
    fun deleteById(id: String)

    @Query("DELETE FROM dog")
    fun deleteAll()



}