package com.example.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.Chat
import com.example.data.ChatUidRepository
import com.example.data.ChatsRepository
import com.example.data.Friend
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChatWithFriendViewModel : ViewModel() {
    private var repository = ChatsRepository()

    private val _chatInfo: MutableStateFlow<Chat> = ChatUidRepository.selectedChat
    val chatInfo : StateFlow<Chat> = _chatInfo


    }
