package com.example.chat_application.ui.activity.bottomScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import com.example.core.ChatsViewModel
import com.example.data.AuthRepository
import com.example.data.ChatsRepository

@Composable
fun AddFriendScreen(viewModel : ChatsViewModel){
    val emptyInput by viewModel.emptyInput.observeAsState(initial = false)
    val errorText by viewModel.errorText.observeAsState(initial = "fill empty fields")
    val successful by viewModel.successful.observeAsState(initial = false)
    val styleInput = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = MaterialTheme.colorScheme.onSurface,
        unfocusedBorderColor = MaterialTheme.colorScheme.onSurface,
        focusedLabelColor = MaterialTheme.colorScheme.onSurface,
        unfocusedLabelColor = MaterialTheme.colorScheme.onSurface
    )

    Surface(contentColor = Color.Black) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier
                    .height(128.dp)
                    .width(128.dp),
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Image friend",
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Add friend",
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(12.dp),
                value = viewModel.fieldText.value,
                onValueChange = {
                    viewModel.setEmail(it)
                    viewModel.setEmail(viewModel.fieldText.value)
                },
                label = { Text(text = "Email", fontSize = 25.sp) },
                colors = styleInput
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {viewModel.sendRequest()
                          viewModel.sendRequest()},
                modifier = Modifier
                    .fillMaxWidth(0.82f)
                    .fillMaxHeight(0.12f)
                    .background(MaterialTheme.colorScheme.onSurface),
                shape = RectangleShape,
            ) {
                Text(
                    text = "Send request",
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 25.sp
                )
            }
            if(emptyInput){
                Text(text = errorText, color = Color.Red, modifier = Modifier
                    .padding(16.dp, 0.dp)
                    .wrapContentSize(Alignment.Center))
            }
            if(successful){
                Text(text = "Request send is successful", modifier = Modifier
                    .padding(16.dp, 0.dp)
                    .wrapContentSize(Alignment.Center))
            }
        }



    }
}