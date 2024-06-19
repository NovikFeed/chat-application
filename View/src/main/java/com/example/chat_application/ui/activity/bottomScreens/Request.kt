package com.example.chat_application.ui.activity.bottomScreens

import android.graphics.Paint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.chat_application.ui.activity.navigation.BottomBarScreen
import com.example.core.RequestViewModel
import com.example.data.RequestToFriend


@Composable
fun RequestScreen(viewModel : RequestViewModel){
    val list by viewModel.friendRequests.collectAsState(initial = emptyList())
    BottomBarScreen.Request.badgedCount = list.size
    Column(modifier = Modifier.padding(0.dp, 40.dp)) {
        if(list.isNotEmpty()) {
            if(list.size == 1){
            Text(text = "You have ${list.size} request:")}
            else{
                Text(text = "You have ${list.size} requests:")}
            LazyColumn {
                items(list){item ->
                    addItem(item = item, viewModel)
                }
            }
        }
        else{
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
                Text(text = "You not have request to add friends", textAlign = TextAlign.Center)
            }
        }
    }

}
@Composable
fun addItem(item : RequestToFriend, viewModel: RequestViewModel){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(8.dp, 16.dp)
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.onSurface,
                shape = RectangleShape
            )
            .padding(8.dp)
    ) {
        Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = rememberImagePainter(
                    data = item.photoUrl,
                    builder = {
                        crossfade(true)
                    }),
                modifier = Modifier
                    .width(150.dp)
                    .height(190.dp)
                    .clip(shape = RoundedCornerShape(32.dp)),
                contentDescription = "Avatar",
                contentScale = ContentScale.Crop
            )
                Column(
                    modifier = Modifier.padding(8.dp, 0.dp, 0.dp, 0.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(text = item.nickname, textAlign = TextAlign.Center)
                    Row(horizontalArrangement = Arrangement.Center) {
                        Button(
                            onClick = { viewModel.toAccept(item.uid) },
                            modifier = Modifier.border(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.onSurface,
                                shape = RectangleShape
                            )
                        ) {
                            Text(
                                text = "Accept",
                                style = MaterialTheme.typography.bodySmall,
                                fontSize = 20.sp,
                                lineHeight = 21.sp
                            )
                        }
                        Button(
                            onClick = {viewModel.toRefuse(item.uid)},
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Refuse",
                                style = MaterialTheme.typography.bodySmall,
                                fontSize = 18.sp,
                                lineHeight = 21.sp
                            )
                        }
                    }
                }

        }
    }
}