package com.example.chat_application.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.chat_application.ui.activity.ui.theme.Chat_applicationTheme
import com.example.core.AuthViewModel
import com.example.core.NavigationManager
import kotlinx.coroutines.delay
import com.example.core.NavigationScreen
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(android.graphics.Color.TRANSPARENT,
                android.graphics.Color.TRANSPARENT)
        )
        setContent {
            Chat_applicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {
    val viewModelLogin = AuthViewModel()
    MainContent( viewModelLogin = viewModelLogin)

    
}
@Composable
fun MainContent(viewModelLogin : AuthViewModel){
    val currentScreen by NavigationManager.currentScreen.observeAsState(NavigationScreen.Loading)
    when(currentScreen){
        NavigationScreen.Loading -> {LoadingScreen()
            LaunchedEffect(Unit) {
                delay(2000)
                NavigationManager.navigateToLater(NavigationScreen.Login)
            }
        }
        NavigationScreen.Login -> LoginFragment(viewModelLogin)
        NavigationScreen.Register -> RegisterFragment(viewModelLogin)
        NavigationScreen.Chats -> ChatsFragment(viewModelLogin)

    }
}

