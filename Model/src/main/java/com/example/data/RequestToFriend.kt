package com.example.data

data class RequestToFriend(
    val uid: String = "",
    val nickname: String = "",
    val photoUrl: String = "",
    var status : Boolean = true
) {

}