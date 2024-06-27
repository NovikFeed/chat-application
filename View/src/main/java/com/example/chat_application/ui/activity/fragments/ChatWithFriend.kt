package com.example.chat_application.ui.activity.fragments

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.simulateHotReload
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.core.ChatWithFriendViewModel
@Composable
fun ChatWithFriends(viewModel : ChatWithFriendViewModel) {
    val infoAboutFriend by viewModel.userInfo.collectAsState()
    val onlineStatus by viewModel.onlineStatus.collectAsState()
    viewModel.setUserInfo()
    Scaffold(topBar = {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.15f)
                .padding(0.dp, 36.dp, 0.dp, 0.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(onClick = { viewModel.backButton() }) {
                    Icon(
                        modifier = Modifier.size(46.dp),
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "back"
                    )

                }
                Image(
                    painter = rememberImagePainter(
                        data = infoAboutFriend.imgUrl,
                        builder = {
                            crossfade(true)
                        }),
                    modifier = Modifier
                        .size(64.dp)
                        .clip(shape = RoundedCornerShape(32.dp)),
                    contentDescription = "Avatar",
                    contentScale = ContentScale.Crop
                )

                Column(modifier = Modifier.padding(30.dp,0.dp,0.dp,0.dp)) {
                    Text(
                        text = if (infoAboutFriend.nickname.length < 12) infoAboutFriend.nickname else infoAboutFriend.nickname.take(
                            12
                        ) + "..", style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = onlineStatus,
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = (20.sp)
                    )

                }
            }
        }
    },
        content = {paddingValues ->
            Text(modifier = Modifier.padding(paddingValues), text = "")
            LazyColumn(){

            }
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.12f)
                    .padding(5.dp, 0.dp, 5.dp, 30.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxHeight(0.99f)
                        .fillMaxWidth(0.8f),

                    value = viewModel.textMessage.value,
                    onValueChange = {
                        viewModel.setText(it)
                    },
                    label = { Text( modifier = Modifier.padding(bottom = 20.dp), text = "massage", fontSize = 20.sp)},
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.onSurface,
                        unfocusedBorderColor = MaterialTheme.colorScheme.onSurface,
                        focusedLabelColor = MaterialTheme.colorScheme.onSurface,
                        unfocusedLabelColor = MaterialTheme.colorScheme.onSurface
                    )
                )
                Button(onClick = {}) {
                    Icon(modifier = Modifier
                        .padding(top = 8.dp)
                        .size(30.dp), imageVector = Icons.Default.PlayArrow, contentDescription = "Send")
                }
            }
        })
}

