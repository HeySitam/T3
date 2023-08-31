package com.thedirone.multiplayer_tic_tac_toe.ui.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.thedirone.multiplayer_tic_tac_toe.core.utils.Vertically

@Composable
fun HomePageScreen(
    onHostBtnClick:() -> Unit,
    onJoinBtnClick:() -> Unit,
){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ElevatedButton(onClick = onHostBtnClick) {
            Text(text = "Host")
        }
        32.Vertically()
        ElevatedButton(onClick = onJoinBtnClick) {
            Text(text = "Join")
        }
    }
}