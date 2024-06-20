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
import androidx.compose.material3.Icon
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
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.core.FriendViewModel
import com.example.core.RequestViewModel
import com.example.data.Friend
import com.example.data.RequestToFriend

@Composable
fun FriendsScreen(viewModel : FriendViewModel){
    val style = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = MaterialTheme.colorScheme.onSurface,
        unfocusedBorderColor = MaterialTheme.colorScheme.onSurface,
        focusedLabelColor = MaterialTheme.colorScheme.onSurface,
        unfocusedLabelColor = MaterialTheme.colorScheme.onSurface
    )
    val friendList by viewModel.searchFriendsList.collectAsState(initial = emptyList())
    val mainFriendList by viewModel.friendsList.collectAsState(initial = emptyList())
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 40.dp, 0.dp, 0.dp)
    ){
        if(mainFriendList.isNotEmpty()){
            Text(text = "You have ${mainFriendList.size} friend${if (mainFriendList.size > 1) "s" else ""}:")
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(12.dp),
                value = viewModel.searchText.value,
                onValueChange = {
                    viewModel.setSearchText(it)
                    viewModel.searchFriend()
                },
                label = { Text(text = "Search", fontSize = 25.sp) },
                colors = style
            )
            LazyColumn(){
                items(friendList){item ->
                    addFriendToScreen(item = item, viewModel = viewModel)
                }
            }
        }
        else{
            Column (modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center){
                Text(text = "You not have friends")
            }
        }
    }
}

@Composable
fun addFriendToScreen(item: Friend, viewModel: FriendViewModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(8.dp, 8.dp)
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.onSurface,
                shape = RectangleShape
            )
            .padding(8.dp)
    ) {
        Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
            Image(
                painter = rememberImagePainter(
                    data = item.photoUrl,
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
            Text(
                text = "${if(item.nickName.length<14) item.nickName else item.nickName.take(12) + ".."}",
                textAlign = TextAlign.Center)

                Button(
                    onClick = { viewModel.startChat(item.chatUid,item.uid)},
                    modifier = Modifier.border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.onSurface,
                        shape = RectangleShape
                    )
                ) {
                    Image(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Chat",
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
                    )
                }
        }
    }
}