package com.example.chat_application.ui.activity

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.chat_application.ui.activity.ui.theme.Images
import com.example.core.AuthViewModel
import java.net.URI

@Composable
fun RegisterFragment(viewModelText : AuthViewModel) {
    val styleInput = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = MaterialTheme.colorScheme.onSurface,
        unfocusedBorderColor = MaterialTheme.colorScheme.onSurface,
        focusedLabelColor = MaterialTheme.colorScheme.onSurface,
        unfocusedLabelColor = MaterialTheme.colorScheme.onSurface
    )
    val textEmail = remember { mutableStateOf("") }
    val textPassword = remember { mutableStateOf("") }
    val textNickname = remember { mutableStateOf("") }

    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    val galleryLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent(), onResult = {
        uri : Uri? -> selectedImageUri = uri
        viewModelText.setUri(uri!!)
        viewModelText.setNonDefaultImage()
    })

    val visibility by viewModelText.emptyInput.observeAsState(initial = false)

    val errorText by viewModelText.errorMassage.observeAsState(initial = "fill in all fields")

    val defaultImage by viewModelText.defaultImage.observeAsState(initial = true)


    Column(modifier = Modifier
        .fillMaxSize(1f)
        .padding(0.dp, 16.dp), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        Button(modifier = Modifier
            .fillMaxWidth(0.5f)
            .fillMaxHeight(0.25f)
            .padding(0.dp, 0.dp, 0.dp, 16.dp),
            onClick = {galleryLauncher.launch("image/*")}){
            if(defaultImage){
                Image(painter = Images.defaultImage, contentDescription = "Logo",
                    modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
            }
            else{
                Image(painter = rememberImagePainter(data = selectedImageUri), contentDescription = "Logo",
                    modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
            }
        }
        Text(text = "Create account", style = MaterialTheme.typography.titleLarge)
        OutlinedTextField(value = textNickname.value, onValueChange = { viewModelText.setNickname(it)
            textNickname.value = it},
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(12.dp),
            label = { Text(text = "Nickname", fontSize = 25.sp) },
            colors = styleInput)
        OutlinedTextField(value = textEmail.value, onValueChange = { viewModelText.setEmail(it)
            textEmail.value = it},
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(12.dp),
            label = { Text(text = "Email", fontSize = 25.sp) },
            colors = styleInput)
        OutlinedTextField(value = textPassword.value, onValueChange = { viewModelText.setPassword(it)
            textPassword.value = it},
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(12.dp),
            label = { Text(text = "Password", fontSize = 25.sp) },
            colors = styleInput,
            visualTransformation = PasswordVisualTransformation()
        )
        Surface {
            Button(modifier = Modifier
                .fillMaxWidth(0.82f)
                .fillMaxHeight(0.28f)
                .padding(16.dp, 16.dp, 16.dp, 8.dp)
                .background(MaterialTheme.colorScheme.onSurface, shape = RectangleShape),
                onClick = {viewModelText.setDataRegister()}) {
                Text(
                    text = "Create", style = MaterialTheme.typography.bodyLarge,
                    color = if (MaterialTheme.colorScheme.onSurface
                            .luminance() > 0.5
                    ) Color.Black else Color.White,
                )
            }
        }
        if(visibility){
            Text(text = errorText, color = Color.Red, modifier = Modifier
                .padding(16.dp, 0.dp)
                .wrapContentSize(Alignment.Center))
        }


    }
}