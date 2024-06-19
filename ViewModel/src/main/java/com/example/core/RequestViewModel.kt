package com.example.core

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.ChatsRepository
import com.example.data.Friend
import com.example.data.RequestToFriend
import com.example.data.User
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RequestViewModel : ViewModel() {
    private val _friendRequests = MutableStateFlow<List<RequestToFriend>>(emptyList())
    val friendRequests: StateFlow<List<RequestToFriend>> = _friendRequests

    private val repository = ChatsRepository()
    init {
        setListenerForRequest()
    }
    private fun setListenerForRequest(){
        try {
            viewModelScope.launch {
                repository.setListenerForRequestList().collect { list ->
                    _friendRequests.value = list
                }
            }
        }
        catch (e : Exception){
            Log.e("Error request listener", e.toString())
        }
    }
    fun toRefuse(uid : String){
        viewModelScope.launch {
            repository.refuseRequest(uid)
        }
    }
    fun toAccept(acceptedUserUid:String){
        viewModelScope.launch {
            val result = repository.acceptRequest(acceptedUserUid)
            if(result.isSuccess){
                toRefuse(acceptedUserUid)
                repository.addMeToFriend(acceptedUserUid)
            }
        }
    }
}