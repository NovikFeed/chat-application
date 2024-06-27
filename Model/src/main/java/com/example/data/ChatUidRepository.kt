package com.example.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

object ChatUidRepository {
    private val _selectedChat = MutableStateFlow<Chat>(Chat())
    val selectedChat = _selectedChat

    private val repository = ChatsRepository()

    fun setSelectedChat(uid : String, firstUserUid : String, secondUserUid : String){
        if(uid != ""){
            GlobalScope.launch {
                _selectedChat.value = repository.getChat(uid)
            }
        }
        else{
            GlobalScope.launch {
                val firstUser = repository.getUser(firstUserUid)!!
                val secondUser = repository.getUser(secondUserUid)!!
                val chatUid = firstUserUid + secondUserUid
                val chat = Chat(firstUserUid, secondUserUid,firstUser.imgUrl, secondUser.imgUrl, firstUser.nickname, secondUser.nickname)
                repository.setChat(chat,chatUid)
                repository.setChatForUsers(chatUid, chat, firstUserUid, secondUserUid)
                _selectedChat.value = chat
            }
        }
    }
}