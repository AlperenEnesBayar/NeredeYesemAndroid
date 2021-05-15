package com.example.getsocialandroid.localdb

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.getsocialandroid.cards.Rest

@Dao
interface RestDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addURest(restdb: RestDb)

    @Query("SELECT * FROM tRest")
    fun readRest(): LiveData<List<RestDb>>

    @Delete
    suspend fun deleteRest(restDb: RestDb)
}