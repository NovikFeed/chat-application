package com.example.data

data class SmallChat(
    val uid : String = "",
    val imgUrl : String = "",
    val lastMessage: Message = Message(),
    val nickname : String = ""
) {
}