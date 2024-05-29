package com.example.chat_application.ui.activity.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.chat_application.R

object Images {
    val logo : Painter
    @Composable
    get() = painterResource(id = R.drawable.logo)
}