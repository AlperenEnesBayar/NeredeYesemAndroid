package com.example.getsocialandroid.api

import com.example.getsocialandroid.model.*
import retrofit2.Response
import retrofit2.http.*

interface SimpleApi
{

    @GET("read_single.php")
    suspend fun getPUserByID(
            @Query("id") userID: Int
    ): Response<Post>

    @POST("login.php")
    suspend fun login(
            @Body loginParams: LoginParams
    ): Response<ResponseCode>

    @POST("register.php")
    suspend fun register(
            @Body registerParams: RegisterParams
    ): Response<ResponseCode>

    @POST("get_rest.php")
    suspend fun get_rest(
            @Body getRestParams: getRestParams
    ): Response<ResponseRest>

}