package com.example.data

import java.time.LocalTime
import java.time.format.DateTimeFormatter

data class Message(
    val text : String = "",
    val senderUid : String = "",
    val time : String = setTime()
)
    fun setTime() : String{
        val time = LocalTime.now()
        val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
        return time.format(formatter)
    }
