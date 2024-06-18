package com.example.chat_application.ui.activity.bottomScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.chat_application.ui.activity.navigation.BottomBarScreen
import com.example.core.ChatsViewModel
import com.example.core.RequestViewModel
import com.example.data.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Scope

@Composable
fun RequestScreen(viewModel : RequestViewModel){
    val list by viewModel.friendRequests.collectAsState(initial = emptyList())
    BottomBarScreen.Request.badgedCount = list.size
    Column {
        list.forEach{item ->
        addItem(item = item, onAccept = {  }, onReject = {})}
    }

}
@Composable
fun addItem(item : User, onAccept: () -> Unit, onReject: () -> Unit){
    Row (modifier = Modifier.fillMaxSize()){
        Text(text = item.nickname )
//        Image(
//            painter = rememberImagePainter(
//                data = item.imgUrl,
//                builder = {
//                    crossfade(true)
//            }),
//            contentDescription = "Avatar")
    }
}