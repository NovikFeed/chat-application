package com.example.core

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.Chat
import com.example.data.ChatsRepository
import com.example.data.Friend
import com.example.data.SmallChat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChatsViewModel : ViewModel() {
    private val repository = ChatsRepository()

    private val _chatsList = MutableStateFlow<List<SmallChat>>(emptyList())
    val chatList: StateFlow<List<SmallChat>> = _chatsList

    init {
        setListenerForChats()
    }

    private fun setListenerForChats() {
        try {
            viewModelScope.launch {
                repository.setListenerForChatsList().collect{list ->
                    _chatsList.value = list
                }
            }
        }
        catch(e : Exception){
            Log.e("Error chats listener", e.toString())

        }
    }

}