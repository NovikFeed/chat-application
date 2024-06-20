package com.example.chat_application.ui.activity.bottomScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.core.AuthViewModel
import com.example.core.NavigationManager
import com.example.core.NavigationScreen
import com.example.data.AuthRepository

@Composable
fun ChatsScreen(){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Red)){
    Button(onClick = {AuthRepository().singOut()
                        NavigationManager.navigateTo(NavigationScreen.Login)}) {
        Text(text = "CLICK")
    }}
}