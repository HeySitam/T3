package com.thedirone.multiplayer_tic_tac_toe.ui.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.thedirone.multiplayer_tic_tac_toe.core.utils.Client

@Composable
fun ClientPageScreen(){
    remember {
        Client(ipAddr = "192.168.0.145")
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Hi I am client")
    }
}