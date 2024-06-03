package com.example.core

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
   private val _emptyInput = MutableLiveData<Boolean>()
    val emptyInput = _emptyInput
    val email= mutableStateOf("")
    val password = mutableStateOf("")

    fun setDataForLogin(){
        if(email.value != "" && password.value != ""){

        }
        else{
            emptyInput.value = true
        }
    }
    fun setEmail(email : String){
       this.email.value = email
        if(emptyInput.value == true){
            emptyInput.value = false
        }
    }
    fun setPassword(password : String){
        this.password.value = password
        if(emptyInput.value == true){
            emptyInput.value = false
        }
    }
}