package com.example.data

data class SmallChat(
    val imgUrl : String = "",
    val lastMessage: Message = Message(),
    val nickname : String = ""
) {
}