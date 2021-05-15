package com.example.getsocialandroid.localdb

import androidx.lifecycle.LiveData

class UserRepository (private val userDao: UserDao) {

    fun readUser(email: String) : LiveData<User>
    {
       return userDao.readUser(email)
    }

    suspend fun addUser(user: User)
    {
        userDao.addUser(user)
    }

}