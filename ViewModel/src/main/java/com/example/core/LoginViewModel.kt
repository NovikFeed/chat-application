package com.example.core

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val _emptyInput = MutableLiveData<Boolean>()
            val emptyInput = _emptyInput

    private val _errorMassage = MutableLiveData<String>()
            val errorMassage = _errorMassage

    private val email= mutableStateOf("")
    private val password = mutableStateOf("")

    private val authRepository = AuthRepository()

    fun setDataForLogin(){
        if(email.value != "" && password.value != ""){
            viewModelScope.launch{
               val result = authRepository.loginToAccount(email.value, password.value)
                Log.i("coord", result.toString())
                if(result.isSuccess){
                    NavigationManager.navigateTo(NavigationScreen.Chats)
                }
                else{
                    _errorMassage.postValue(result.toString().substringAfter(":"))
                    _emptyInput.postValue(true)


                }
            }
        }
        else{
            _errorMassage.value = "fill in all fields"
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