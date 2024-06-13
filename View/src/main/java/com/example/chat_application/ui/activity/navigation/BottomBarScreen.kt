package com.example.chat_application.ui.activity.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route : String,
    val title : String,
    val icon : ImageVector
) {
    object Chats : BottomBarScreen(
        route = "chats",
        title = "Chats",
        icon = Icons.Default.MailOutline
    )
    object Friends : BottomBarScreen(
        route = "friends",
        title = "Friends",
        icon = Icons.Default.Face
    )
    object Request : BottomBarScreen(
        route = "request",
        title = "Request",
        icon = Icons.Default.Notifications
    )
    object AddFriend : BottomBarScreen(
        route = "addFriend",
        title = "Add Friend",
        icon = Icons.Default.AddCircle
    )
}