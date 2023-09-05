package com.thedirone.multiplayer_tic_tac_toe.features.ui.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.thedirone.multiplayer_tic_tac_toe.core.utils.Horizontally

@Composable
fun DummyBoxGrid() {
    Column() {
        Row {
            Button(
                onClick = { /*TODO*/ }
            ) {
                Text(text = "1")
            }
            8.Horizontally()
            Button(onClick = { /*TODO*/ }) {
                Text(text = "2")
            }
            8.Horizontally()
            Button(onClick = { /*TODO*/ }) {
                Text(text = "3")
            }
        }
        Row() {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "4")
            }
            8.Horizontally()
            Button(onClick = { /*TODO*/ }) {
                Text(text = "5")
            }
            8.Horizontally()
            Button(onClick = { /*TODO*/ }) {
                Text(text = "6")
            }
        }
        Row() {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "7")
            }
            8.Horizontally()
            Button(onClick = { /*TODO*/ }) {
                Text(text = "8")
            }
            8.Horizontally()
            Button(onClick = { /*TODO*/ }) {
                Text(text = "9")
            }
        }
    }
}