package com.example.getsocialandroid

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.getsocialandroid.model.*
import com.example.getsocialandroid.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {

    val myResponse: MutableLiveData<Response<Post>> = MutableLiveData()
    val loginResponse: MutableLiveData<Response<ResponseLogin>> = MutableLiveData()
    val registerResponse: MutableLiveData<Response<ResponseCode>> = MutableLiveData()
    val restResponse: MutableLiveData<Response<ResponseRest>> = MutableLiveData()
    val restidResponse: MutableLiveData<Response<ResponseRest2>> = MutableLiveData()

    fun getPUserByID(userID: Int)
    {
        viewModelScope.launch{
            val response = repository.getPUserByID(userID)
            myResponse.value = response
        }
    }

    fun login(loginParams: LoginParams)
    {
        viewModelScope.launch{
            val response = repository.login(loginParams)
            loginResponse.value = response
        }
    }

    fun register(registerParams: RegisterParams)
    {
        viewModelScope.launch{
            val response = repository.register(registerParams)
            registerResponse.value = response
        }
    }

    fun getRest(restParams: getRestParams)
    {
        viewModelScope.launch {
            val response = repository.getRest(restParams)
            restResponse.value = response
        }
    }

    fun getRestid(restParams: getRestParams2)
    {
        viewModelScope.launch {
            val response = repository.getRestid(restParams)
            restidResponse.value = response
        }
    }
}