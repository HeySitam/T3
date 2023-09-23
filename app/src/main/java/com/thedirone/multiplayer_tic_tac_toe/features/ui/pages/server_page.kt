package com.thedirone.multiplayer_tic_tac_toe.features.ui.pages

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Warning
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.thedirone.multiplayer_tic_tac_toe.core.utils.Server
import com.thedirone.multiplayer_tic_tac_toe.core.utils.Vertically
import com.thedirone.multiplayer_tic_tac_toe.features.ui.widgets.AppAlertDialog
import com.thedirone.multiplayer_tic_tac_toe.features.ui.widgets.GameBoard
import com.thedirone.multiplayer_tic_tac_toe.features.viewmodels.ServerViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServerPageScreen(navController: NavController) {
    val serverVM: ServerViewModel = viewModel()
    val statusMsgState = serverVM.serverStatus.observeAsState()
    val gameArray = serverVM.gameArrayInfo.observeAsState()
    val isOpponentWin = serverVM.isOpponentWon.observeAsState()
    val amIWon = serverVM.amIWon.observeAsState()
    val receivedData = serverVM.receivedDataFromClient.observeAsState()
    var text by rememberSaveable { mutableStateOf("Text from Server") }
    remember {
        serverVM.apply {
            startServer()
        }
        null
    }
    // Handling onBackPressed
    BackHandler() {
        serverVM.closeServer()
        navController.navigateUp()
    }
    if (isOpponentWin.value == true) {
        AppAlertDialog(
            onPlayAgainRequest = {
                serverVM.resetGame()
            },
            onExitRequest = {
                navController.navigateUp()
                serverVM.closeServer()
            },
            dialogTitle = "You Loose!",
            dialogText = "😢Better luck next time😢",
            icon = Icons.Default.Warning
        )
    }

    if (amIWon.value == true) {
        AppAlertDialog(
            onPlayAgainRequest = {
                serverVM.resetGame()
            },
            onExitRequest = {
                navController.navigateUp()
                serverVM.closeServer()
            },
            dialogTitle = "Booyah!",
            dialogText = "You won the match✌️",
            icon = Icons.Default.Done
        )
    }

    GameBoard(gameArr = gameArray.value ?: IntArray(9), statusMsg = statusMsgState.value) { pos ->
        if (serverVM.isServerTurn) {
            serverVM.sendDataWithPositionToClient(pos = pos)
        }
        Log.d("SelectedPos", pos.toString())
    }
}