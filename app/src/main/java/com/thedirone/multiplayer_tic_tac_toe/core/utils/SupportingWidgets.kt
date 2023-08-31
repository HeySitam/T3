package com.thedirone.multiplayer_tic_tac_toe.core.utils

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Int.Vertically() = Spacer(modifier = Modifier.height(this.dp))

@Composable
fun Int.Horizontally() = Spacer(modifier = Modifier.width(this.dp))