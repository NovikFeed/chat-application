package com.example.core

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.Chat
import com.example.data.ChatUidRepository
import com.example.data.ChatsRepository
import com.example.data.Friend
import com.example.data.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.cache
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

class ChatWithFriendViewModel : ViewModel() {
    private val repository = ChatsRepository()
    private val _chatInfo: MutableStateFlow<Chat> = ChatUidRepository.selectedChat
    val chatInfo : StateFlow<Chat> = _chatInfo

    private val _userInfo: MutableStateFlow<User> = MutableStateFlow(User())
    val userInfo : StateFlow<User> = _userInfo

    private val _onlineStatus: MutableStateFlow<String> = MutableStateFlow("")
    val onlineStatus : StateFlow<String> = _onlineStatus

    fun setUserInfo(){
        viewModelScope.launch {
            var uidUser = ""
            _chatInfo
                .filter { chat -> chat.firstNickname.isNotEmpty() }
                .collectLatest {chat ->
                val firstUid = chat.firstUserUid
                val secondUid = chat.secondUserUid
                uidUser = repository.checkUid(firstUid,secondUid)
                    _userInfo.value = repository.getUser(uidUser)!!
                    _userInfo.collectLatest { i ->
                        repository.setListenerForOnlineStatus(uidUser).collect(){status ->
                            _onlineStatus.value = status
                        }

                    }

            }
        }
    }
    fun backButton(){
        NavigationManager.navigateTo(NavigationScreen.Chats)
    }
    }
