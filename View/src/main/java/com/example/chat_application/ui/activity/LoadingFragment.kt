package com.example.chat_application.ui.activity

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.chat_application.R
import com.example.chat_application.ui.activity.ui.theme.Images

@Composable
fun LoadingScreen(){

     Surface(modifier = Modifier
         .fillMaxSize(), shape = RectangleShape){
         Column(modifier = Modifier.fillMaxSize().background(Color.Cyan), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
             Image(painter = Images.logo, contentDescription = "App logo")
             Text(text = "Chatty", style = MaterialTheme.typography.titleLarge)


             }
         }
     }