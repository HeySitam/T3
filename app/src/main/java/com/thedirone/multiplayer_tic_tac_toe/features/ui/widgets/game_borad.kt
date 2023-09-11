package com.thedirone.multiplayer_tic_tac_toe.features.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.thedirone.multiplayer_tic_tac_toe.R

val paddingForCross = 8.dp
val internalPaddingForCircle = 12.dp
val externalPaddingForCircle = 8.dp
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameBoard() {
    Scaffold(
        topBar = { TopAppBar(
            modifier = Modifier.fillMaxWidth(),
            title={Text("Test",
                modifier= Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )})  }
    ) {innerPadding ->
        Column(
            modifier = Modifier.background(color = Color(0xffCDCDCD))
                .fillMaxSize()
                .padding(innerPadding)
                .padding(8.dp)
        ) {
            Row(modifier = Modifier
                .fillMaxHeight()
                .weight(1f)) {
                Image(
                    painter = painterResource(id = R.drawable.cross),
                    contentDescription = "Cross Icon",
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                        .padding(horizontal = paddingForCross, vertical = paddingForCross)
                        .background(color = Color.White, shape = RoundedCornerShape(size = 8.dp))
                        .padding(horizontal = paddingForCross, vertical = paddingForCross)
                )
                Image(
                    painter = painterResource(id = R.drawable.zero),
                    contentDescription = "Zero Icon",
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                        .padding(
                            horizontal = externalPaddingForCircle,
                            vertical = externalPaddingForCircle
                        )
                        .background(color = Color.White, shape = RoundedCornerShape(size = 8.dp))
                        .padding(
                            horizontal = internalPaddingForCircle,
                            vertical = internalPaddingForCircle
                        )
                )
                Image(
                    painter = painterResource(id = R.drawable.cross),
                    contentDescription = "Cross Icon",
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                        .padding(horizontal = paddingForCross, vertical = paddingForCross)
                        .background(color = Color.White, shape = RoundedCornerShape(size = 8.dp))
                        .padding(horizontal = paddingForCross, vertical = paddingForCross)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.cross),
                    contentDescription = "Cross Icon",
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                        .padding(horizontal = paddingForCross, vertical = paddingForCross)
                        .background(color = Color.White, shape = RoundedCornerShape(size = 8.dp))
                        .padding(horizontal = paddingForCross, vertical = paddingForCross)
                )
                Image(
                    painter = painterResource(id = R.drawable.zero),
                    contentDescription = "Zero Icon",
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                        .padding(
                            horizontal = externalPaddingForCircle,
                            vertical = externalPaddingForCircle
                        )
                        .background(color = Color.White, shape = RoundedCornerShape(size = 8.dp))
                        .padding(
                            horizontal = internalPaddingForCircle,
                            vertical = internalPaddingForCircle
                        )
                )
                Image(
                    painter = painterResource(id = R.drawable.cross),
                    contentDescription = "Cross Icon",
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                        .padding(horizontal = paddingForCross, vertical = paddingForCross)
                        .background(color = Color.White, shape = RoundedCornerShape(size = 8.dp))
                        .padding(horizontal = paddingForCross, vertical = paddingForCross)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.cross),
                    contentDescription = "Cross Icon",
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                        .padding(horizontal = paddingForCross, vertical = paddingForCross)
                        .background(color = Color.White, shape = RoundedCornerShape(size = 8.dp))
                        .padding(horizontal = paddingForCross, vertical = paddingForCross)
                )
                Image(
                    painter = painterResource(id = R.drawable.zero),
                    contentDescription = "Zero Icon",
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                        .padding(
                            horizontal = externalPaddingForCircle,
                            vertical = externalPaddingForCircle
                        )
                        .background(color = Color.White, shape = RoundedCornerShape(size = 8.dp))
                        .padding(
                            horizontal = internalPaddingForCircle,
                            vertical = internalPaddingForCircle
                        )
                )
                Image(
                    painter = painterResource(id = R.drawable.cross),
                    contentDescription = "Cross Icon",
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                        .padding(horizontal = paddingForCross, vertical = paddingForCross)
                        .background(color = Color.White, shape = RoundedCornerShape(size = 8.dp))
                        .padding(horizontal = paddingForCross, vertical = paddingForCross)

                )
            }
        }
    }

}