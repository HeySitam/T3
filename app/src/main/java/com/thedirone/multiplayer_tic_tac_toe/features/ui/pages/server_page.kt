package com.thedirone.multiplayer_tic_tac_toe.features.ui.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.thedirone.multiplayer_tic_tac_toe.core.utils.Server
import com.thedirone.multiplayer_tic_tac_toe.core.utils.Vertically
import com.thedirone.multiplayer_tic_tac_toe.features.viewmodels.ServerViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServerPageScreen() {
    val serverVM: ServerViewModel = viewModel()
    val statusMsgState = serverVM.serverStatus.observeAsState()
    val receivedData = serverVM.receivedDataFromClient.observeAsState()
    var text by rememberSaveable { mutableStateOf("Text from Server") }
    remember {
        serverVM.apply {
            startServer()
//           sendData(1234)
//           receiveData()
        }
        null
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "${receivedData.value}")
        16.Vertically()
        TextField(
            value = text,
            onValueChange = {
                text = it
            },
            label = { Text("Server") }
        )
        16.Vertically()
        ElevatedButton(onClick = {
            serverVM.sendData(text.toInt())
        }) {
            Text("Send to Client")
        }
        16.Vertically()
        Text(text = "${statusMsgState.value}")
    }
}