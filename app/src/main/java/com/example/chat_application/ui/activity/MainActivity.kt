package com.example.chat_application.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.chat_application.ui.activity.ui.theme.Chat_applicationTheme
import com.example.core.NavigationViewModel
import com.example.data.NavigationScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
    val viewModel = NavigationViewModel()
    MainContent(viewModel = viewModel)

    
}
@Composable
fun MainContent(viewModel: NavigationViewModel){
    val currentScreen by viewModel.currentScreen.observeAsState(NavigationScreen.Loading)
    when(currentScreen){
        NavigationScreen.Loading -> LoadingFragment()
        NavigationScreen.Login -> LoginFragment()
        NavigationScreen.Register -> RegisterFragment()

    }
}

