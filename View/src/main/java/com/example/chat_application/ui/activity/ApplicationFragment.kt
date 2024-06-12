package com.example.chat_application.ui.activity

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.core.AuthViewModel

@Composable
fun ApplicationFragment(viewModelText : AuthViewModel){
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {}
    ){

    }
}