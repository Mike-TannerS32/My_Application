package com.example.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    private val _loginResult = MutableLiveData<Boolean>();
    val loginResultLiveData = _loginResult

    fun areCredentialsValid(username: String,password: String): Boolean{

        return username == password
    }
}