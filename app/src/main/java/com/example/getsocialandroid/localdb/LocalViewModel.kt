package com.example.getsocialandroid.localdb

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.getsocialandroid.cards.Rest
import com.example.getsocialandroid.cards.RestAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class LocalViewModel(application: Application): AndroidViewModel(application) {

    private val repository: UserRepository
    private val repository2: RestRepository

    init {
        val userDao = DatabaseClass.getDatabase(application).userDao()
        repository = UserRepository(userDao)

        val restDao = DatabaseClass.getDatabase(application).restDao()
        repository2 = RestRepository(restDao)
    }

    fun addUser(user: User)
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            repository.addUser(user)
        }
    }

    fun readUser(email: String) : LiveData<User>
    {
        return repository.readUser(email)
    }

    suspend fun addRest(restdb: RestDb)
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            repository2.addRest(restdb)
        }
    }

    fun readRest(): LiveData<List<RestDb>>
    {
       return repository2.readRest()
    }

    fun deleteRest(restdb: RestDb)
    {
        viewModelScope.async{
            repository2.deleteRest(restdb)
        }
    }
}