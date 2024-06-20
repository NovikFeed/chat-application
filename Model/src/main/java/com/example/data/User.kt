package com.example.data


data class User(
    val nickname: String = "",
    val email: String = "",
    val imgUrl: String = "",
    val chats: List<Chat> = listOf(),
    val friends: List<Friend> = listOf(),
    val request: List<Chat> = listOf()
) {
}