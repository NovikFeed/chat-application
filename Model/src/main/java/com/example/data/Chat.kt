package com.example.data

data class Chat(
    val firstUserUid : String = "",
    val secondUserUid : String ="",
    val firstPhotoUrl : String = "",
    val secondPhotoUrl : String = "",
    val firstNickname : String = "",
    val secondNickname : String = "",
    val messenger: HashMap<String,Message> = HashMap()
)