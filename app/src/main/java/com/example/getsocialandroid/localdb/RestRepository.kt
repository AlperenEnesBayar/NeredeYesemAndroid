package com.example.getsocialandroid.localdb

import androidx.lifecycle.LiveData
import com.example.getsocialandroid.cards.Rest

class RestRepository(private val restDao: RestDao) {
    fun readRest(): LiveData<List<RestDb>> {
        return restDao.readRest()
    }

    suspend fun addRest(restdb: RestDb) {
        restDao.addURest(restdb)
    }

    suspend fun deleteRest(restdb: RestDb)
    {
        restDao.deleteRest(restdb)
    }
}