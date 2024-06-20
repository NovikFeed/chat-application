package com.example.core

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.ChatsRepository
import com.example.data.Friend
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FriendViewModel : ViewModel() {
    private val repository = ChatsRepository()

    private val _friendsList = MutableStateFlow<List<Friend>>(emptyList())
    val friendsList: StateFlow<List<Friend>> = _friendsList

    private val _searchFriendsList = MutableStateFlow<List<Friend>>(emptyList())
    val searchFriendsList: StateFlow<List<Friend>> = _searchFriendsList

    private val _searchText = mutableStateOf("")
    val searchText get() = _searchText

    init {
        setListenerForFriendsList()
    }

    private fun setListenerForFriendsList(){
        try {
            viewModelScope.launch {
                repository.setListenerForFriendsList().collect{list ->
                    _friendsList.value = list
                    _searchFriendsList.value = list
                }
            }
        }
        catch(e : Exception){
            Log.e("Error friends listener", e.toString())

        }
    }
    fun searchFriend(){
        _searchFriendsList.value = if (searchText.value != "") {
            friendsList.value.filter { it.nickName.contains(searchText.value, ignoreCase = true) }
        } else {
            friendsList.value
        }
    }
    fun setSearchText(text : String){
        _searchText.value = text
    }
}