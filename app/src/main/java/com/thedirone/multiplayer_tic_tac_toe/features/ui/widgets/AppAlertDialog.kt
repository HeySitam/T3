package com.thedirone.multiplayer_tic_tac_toe.features.ui.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.DialogProperties

@Composable
fun AppAlertDialog(
    onExitRequest: () -> Unit,
    onPlayAgainRequest: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
) {
    AlertDialog(
        properties = DialogProperties(dismissOnBackPress = false,dismissOnClickOutside = false),
        icon = {
            Icon(icon, contentDescription = "Dialog Icon")
        },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        onDismissRequest = {
            onPlayAgainRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onExitRequest()
                }
            ) {
                Text("Exit")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onPlayAgainRequest()
                }
            ) {
                Text("Play Again")
            }
        }
    )
}