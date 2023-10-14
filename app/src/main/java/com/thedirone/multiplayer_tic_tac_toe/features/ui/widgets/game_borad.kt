package com.thedirone.multiplayer_tic_tac_toe.features.ui.widgets

import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.thedirone.multiplayer_tic_tac_toe.R

val paddingForCross = 8.dp
val internalPaddingForCircle = 12.dp
val externalPaddingForCircle = 8.dp

@Composable
fun ImgSrc(gameData: Int): Painter {
    return when (gameData) {
        1 -> painterResource(id = R.drawable.cross)
        2 -> painterResource(
            id = R.drawable.zero
        )
        else -> painterResource(id = R.drawable.transparent_img)
    }
}

fun getExternalPadding(gameData: Int): Dp {
    return if (gameData == 1) paddingForCross else externalPaddingForCircle
}

fun getInternalPadding(gameData: Int): Dp {
    return if (gameData == 1) paddingForCross else internalPaddingForCircle
}

fun getContentDescription(gameData: Int): String {
    return if(gameData == 1) "Cross Icon" else if(gameData == 2) "Circle Icon" else "Empty"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameBoard(gameArr: IntArray, statusMsg: String?, onClickedBtn: (pos: Int) -> Unit, onResetButtonClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xff0E121A) ),
                title = {
                    Text(
                        statusMsg ?: "",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                },
                actions = {
                    IconButton(onClick = onResetButtonClick) {
                        Icon( imageVector = Icons.Rounded.Refresh, contentDescription = "Reset Game Icon",
                        modifier = Modifier.size(32.dp),
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .background(color = Color(0xffCDCDCD))
                .fillMaxSize()
                .padding(innerPadding)
                .padding(8.dp)
        ) {
            // First Row
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            ) {
                Image(
                    painter = ImgSrc(gameData = gameArr[0]),
                    contentDescription = getContentDescription(gameData = gameArr[0]),
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                        .padding(getExternalPadding(gameData = gameArr[0]))
                        .background(color = Color.White, shape = RoundedCornerShape(size = 8.dp))
                        .clickable { onClickedBtn(0) }
                        .padding(getInternalPadding(gameData = gameArr[0]))
                )
                Image(
                    painter = ImgSrc(gameData = gameArr[1]),
                    contentDescription = getContentDescription(gameData = gameArr[1]),
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                        .padding(getExternalPadding(gameData = gameArr[1]))
                        .background(color = Color.White, shape = RoundedCornerShape(size = 8.dp))
                        .clickable { onClickedBtn(1) }
                        .padding(getInternalPadding(gameData = gameArr[1]))
                )
                Image(
                    painter = ImgSrc(gameData = gameArr[2]),
                    contentDescription = getContentDescription(gameData = gameArr[2]),
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                        .padding(getExternalPadding(gameData = gameArr[2]))
                        .background(color = Color.White, shape = RoundedCornerShape(size = 8.dp))
                        .clickable { onClickedBtn(2) }
                        .padding(getInternalPadding(gameData = gameArr[2]))
                )
            }

            // Second Row
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            ) {
                Image(
                    painter = ImgSrc(gameData = gameArr[3]),
                    contentDescription = getContentDescription(gameData = gameArr[3]),
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                        .padding(getExternalPadding(gameData = gameArr[3]))
                        .background(color = Color.White, shape = RoundedCornerShape(size = 8.dp))
                        .clickable { onClickedBtn(3) }
                        .padding(getInternalPadding(gameData = gameArr[3]))

                )
                Image(
                    painter = ImgSrc(gameData = gameArr[4]),
                    contentDescription = getContentDescription(gameData = gameArr[4]),
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                        .padding(getExternalPadding(gameData = gameArr[4]))
                        .background(color = Color.White, shape = RoundedCornerShape(size = 8.dp))
                        .clickable { onClickedBtn(4) }
                        .padding(getInternalPadding(gameData = gameArr[4]))
                )
                Image(
                    painter = ImgSrc(gameData = gameArr[5]),
                    contentDescription = getContentDescription(gameData = gameArr[5]),
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                        .padding(getExternalPadding(gameData = gameArr[5]))
                        .background(color = Color.White, shape = RoundedCornerShape(size = 8.dp))
                        .clickable { onClickedBtn(5) }
                        .padding(getInternalPadding(gameData = gameArr[5]))
                )
            }

            // Third Row
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            ) {
                Image(
                    painter = ImgSrc(gameData = gameArr[6]),
                    contentDescription = getContentDescription(gameData = gameArr[6]),
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                        .padding(getExternalPadding(gameData = gameArr[6]))
                        .background(color = Color.White, shape = RoundedCornerShape(size = 8.dp))
                        .clickable { onClickedBtn(6) }
                        .padding(getInternalPadding(gameData = gameArr[6]))
                )
                Image(
                    painter = ImgSrc(gameData = gameArr[7]),
                    contentDescription = getContentDescription(gameData = gameArr[7]),
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                        .padding(getExternalPadding(gameData = gameArr[7]))
                        .background(color = Color.White, shape = RoundedCornerShape(size = 8.dp))
                        .clickable { onClickedBtn(7) }
                        .padding(getInternalPadding(gameData = gameArr[7]))
                )
                Image(
                    painter = ImgSrc(gameData = gameArr[8]),
                    contentDescription = getContentDescription(gameData = gameArr[8]),
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                        .padding(getExternalPadding(gameData = gameArr[8]))
                        .background(color = Color.White, shape = RoundedCornerShape(size = 8.dp))
                        .clickable { onClickedBtn(8) }
                        .padding(getInternalPadding(gameData = gameArr[8]))

                )
            }
        }
    }

}