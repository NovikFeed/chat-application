package com.example.chat_application.ui.activity.bottomScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.core.AuthViewModel
import com.example.core.ChatsViewModel
import com.example.core.FriendViewModel
import com.example.core.NavigationManager
import com.example.core.NavigationScreen
import com.example.data.AuthRepository
import com.example.data.Chat
import com.example.data.Friend
import com.example.data.SmallChat

@Composable
fun ChatsScreen(viewModel: ChatsViewModel){
    val friendList by viewModel.chatList.collectAsState(initial = emptyList())
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 40.dp, 0.dp, 0.dp)
    ){
        if(friendList.isNotEmpty()){
            Text(text = "You have ${friendList.size} chat${if (friendList.size > 1) "s" else ""}:")
            LazyColumn(){
                items(friendList){item ->
                    addFriendToScreen(item = item, viewModel = viewModel)
                }
            }
        }
        else{
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "You not have chat")
            }
        }
    }
}

@Composable
fun addFriendToScreen(item: SmallChat, viewModel: ChatsViewModel) {
    Button(
        onClick = {viewModel.startChat(item.uid)},
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(8.dp, 8.dp)
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.onSurface,
                shape = RectangleShape
            )
            .padding(8.dp)
    ) {
        Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceAround) {
            Image(
                painter = rememberImagePainter(
                    data = item.imgUrl,
                    builder = {
                        crossfade(true)
                    }),
                modifier = Modifier
                    .width(80.dp)
                    .height(90.dp)
                    .clip(shape = RoundedCornerShape(32.dp)),
                contentDescription = "Avatar",
                contentScale = ContentScale.Crop
            )
            Column {
                Text(
                    text = "Chat with: ${if(item.nickname.length<9) item.nickname else item.nickname.take(8) + ".."}",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge)
                Row (){
                    Text(text = (if(item.lastMessage.text != "") {if(item.lastMessage.text.length<20) item.lastMessage.text else item.lastMessage.text.take(18) + ".."} else "no messages"),
                        style = MaterialTheme.typography.bodyMedium)
                    Text(
                        modifier = Modifier.padding(10.dp, 4.dp, 0.dp, 0.dp),
                        text = if(item.lastMessage.text != "") item.lastMessage.time else "",
                        style = MaterialTheme.typography.bodySmall
                    )

                }

            }
        }
    }
}