package com.example.data


data class User(
    val nickname: String = "",
    val email: String = "",
    val imgUrl: String = "",
    val chats: HashMap<String,Chat> = HashMap(),
    val friends: HashMap<String,Friend> = HashMap(),
    val request: List<RequestToFriend> = listOf()
) {
}