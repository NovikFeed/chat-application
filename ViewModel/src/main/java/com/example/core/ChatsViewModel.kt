package com.example.core

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.ChatsRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ChatsViewModel : ViewModel() {
    private val _emptyInput = MutableLiveData<Boolean>()
    val emptyInput = _emptyInput

    private val _successful  = MutableLiveData<Boolean>()
    val successful = _successful

    private val _errorText = MutableLiveData<String>()
    val errorText = _errorText

    private val _fieldText = mutableStateOf("")
    val fieldText get() = _fieldText

    fun setEmail(email : String){
        this.fieldText.value = email
        _emptyInput.value = false
    }
    private fun clearEmail(){
        _fieldText.value = ""
    }
    fun sendRequest(){
        if(_fieldText.value != ""){
            viewModelScope.launch {
                val result = ChatsRepository().sendRequest(_fieldText.value)
                if (result.isFailure) {
                    _errorText.postValue(result.toString())
                    _emptyInput.postValue(true)
                }
                else{
                    clearEmail()
                    displaySuccessful()
                }
            }
        }
        else{
            _errorText.value = "fill empty fields"
            _emptyInput.value = true
        }
    }
    private suspend fun displaySuccessful(){
        viewModelScope.launch {
            _successful.postValue(true)
            delay(2000)
            _successful.postValue(false)
        }
    }
}