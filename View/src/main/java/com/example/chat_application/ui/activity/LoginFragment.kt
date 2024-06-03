package com.example.chat_application.ui.activity

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chat_application.ui.activity.ui.theme.Images
import com.example.core.LoginViewModel
import com.example.core.NavigationViewModel
import com.example.core.NavigationScreen

@Composable
fun LoginFragment(viewModel : NavigationViewModel, viewModelText: LoginViewModel) {
    val styleInput = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = MaterialTheme.colorScheme.onSurface,
        unfocusedBorderColor = MaterialTheme.colorScheme.onSurface,
        focusedLabelColor = MaterialTheme.colorScheme.onSurface,
        unfocusedLabelColor = MaterialTheme.colorScheme.onSurface
    )
    val textEmail = remember { mutableStateOf("") }
    val textPassword = remember { mutableStateOf("") }

    val visibility by viewModelText.emptyInput.observeAsState(false)
     Column(modifier = Modifier
         .fillMaxSize(1f)
         .padding(0.dp, 16.dp), horizontalAlignment = Alignment.CenterHorizontally,
         verticalArrangement = Arrangement.Center){
         Image(painter = Images.logo, contentDescription = "Logo")
         Text(text = "Login to your account", style = MaterialTheme.typography.titleLarge)
         OutlinedTextField(value = textEmail.value, onValueChange = { viewModelText.setEmail(it)
                                                                     textEmail.value = it},
             modifier = Modifier.padding(12.dp),
             label = { Text(text = "Email", fontSize = 25.sp)},
             colors = styleInput)
         OutlinedTextField(value = textPassword.value, onValueChange = { viewModelText.setPassword(it)
             textPassword.value = it},
             modifier = Modifier.padding(12.dp),
             label = { Text(text = "Password", fontSize = 25.sp)},
             colors = styleInput,
             visualTransformation = PasswordVisualTransformation())
         Button(modifier = Modifier
             .fillMaxWidth(0.82f)
             .fillMaxHeight(0.28f)
             .padding(16.dp, 16.dp, 16.dp, 8.dp)
             .background(MaterialTheme.colorScheme.onSurface, shape = RectangleShape),
             onClick = {viewModelText.setDataForLogin()}) {
             Text(text = "Log in",style = MaterialTheme.typography.bodyLarge, color = if (MaterialTheme.colorScheme.onSurface
                 .luminance() > 0.5) Color.Black else Color.White,)
         }
         Button(onClick = {viewModel.navigateTo(NavigationScreen.Register)}, Modifier.background(Color.Transparent)) {
             Text(text = "create new account",
                 style = MaterialTheme.typography.bodyMedium,
                 color = (MaterialTheme.colorScheme.onSurface))
         }
         if(visibility){
             Text(text = "fill in all fields", color = Color.Red)
         }


    }
}