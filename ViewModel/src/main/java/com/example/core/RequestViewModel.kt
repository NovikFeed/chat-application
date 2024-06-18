package com.example.core

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.ChatsRepository
import com.example.data.User
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RequestViewModel : ViewModel() {
    private val _friendRequests = MutableStateFlow<List<User>>(emptyList())
    val friendRequests: StateFlow<List<User>> = _friendRequests
    init {
        setListenerForRequest()
    }
    private fun setListenerForRequest() : List<String>{
        val repository = ChatsRepository()
        var res = listOf<String>()
        viewModelScope.launch {
            repository.setListenerForRequestList().collect { list ->
                res = list
            }
        }
        return res
    }
}