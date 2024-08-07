package com.example.chat_application.ui.activity.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.chat_application.ui.activity.bottomScreens.AddFriendScreen
import com.example.chat_application.ui.activity.bottomScreens.ChatsScreen
import com.example.chat_application.ui.activity.bottomScreens.FriendsScreen
import com.example.chat_application.ui.activity.bottomScreens.RequestScreen
import com.example.core.ChatsViewModel
import com.example.core.RequestViewModel

@Composable
fun BottomNavigationGraph(navController: NavHostController){
     val viewModel = ChatsViewModel()
    val viewModelRequest = RequestViewModel()
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Chats.route){
        composable(BottomBarScreen.Chats.route){
            ChatsScreen()
        }
        composable(BottomBarScreen.Friends.route){
            FriendsScreen()
        }
        composable(BottomBarScreen.Request.route){
            RequestScreen(viewModel = viewModelRequest)
        }
        composable(BottomBarScreen.AddFriend.route){
            AddFriendScreen(viewModel = viewModel)
        }
    }
}
