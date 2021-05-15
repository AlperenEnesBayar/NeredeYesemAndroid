package com.example.getsocialandroid.repository

import com.example.getsocialandroid.api.RetrofitInstance
import com.example.getsocialandroid.model.*
import retrofit2.Response

class Repository {

    suspend fun getPUserByID(userId: Int): Response<Post>
    {
        return RetrofitInstance.api.getPUserByID(userId)
    }

    suspend fun login(loginParams: LoginParams): Response<ResponseCode>
    {
        return RetrofitInstance.api.login(loginParams)
    }

    suspend fun register(registerParams: RegisterParams): Response<ResponseCode>
    {
        return RetrofitInstance.api.register(registerParams)
    }

    suspend fun getRest(restParams: getRestParams): Response<ResponseRest>
    {
        return RetrofitInstance.api.get_rest(restParams)
    }
}