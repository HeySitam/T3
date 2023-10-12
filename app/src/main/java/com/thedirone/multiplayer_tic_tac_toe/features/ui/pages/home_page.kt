package com.thedirone.multiplayer_tic_tac_toe.features.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thedirone.multiplayer_tic_tac_toe.R
import com.thedirone.multiplayer_tic_tac_toe.core.utils.Horizontally
import com.thedirone.multiplayer_tic_tac_toe.core.utils.Vertically

@Composable
fun HomePageScreen(
    onHostBtnClick:() -> Unit,
    onJoinBtnClick:() -> Unit,
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xff0E121A))
        ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "avatar",
                contentScale = ContentScale.Inside,            // crop the image if it's not a square
                modifier = Modifier
                    .size(140.dp)
                    .clip(CircleShape)                       // clip to the circle shape
                    .border(2.dp, Color.Gray, CircleShape)   // add a border (optional)
            )
            24.Vertically()
            Text(text = "T3",
                fontSize = 40.sp,
                color = Color.White,
                fontFamily = FontFamily.Cursive
                )
            4.Vertically()
            Text(text = "Tic Tac Toe",
                fontSize = 18.sp,
                color = Color.White
            )
        }
        Column(
            modifier = Modifier.weight(1f)
        ) {
            ActionButton(leadingIcon = Icons.Rounded.Create, btnText = "Host", onBtnClick = onHostBtnClick)
            24.Vertically()
            ActionButton(leadingIcon = Icons.Rounded.Add, btnText = "Join", onBtnClick = onJoinBtnClick)
        }
    }
}

@Composable
fun ActionButton(leadingIcon: ImageVector, btnText: String, onBtnClick: () -> Unit){
    Row(
        verticalAlignment= Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .padding(horizontal = 16.dp)
            .background(
                color = Color(0xffF7C846),
                shape = RoundedCornerShape(12.dp)
            )
            .clickable(onClick = onBtnClick)
    ) {
        12.Horizontally()
        Icon(
            imageVector = leadingIcon,
            contentDescription = "Lock Icon",
            tint = Color.Black
        )
        8.Horizontally()
        Text(text = btnText,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier
            .fillMaxHeight()
            .weight(1f))
        Icon(imageVector = Icons.Rounded.KeyboardArrowRight, contentDescription = "Lock Icon",tint = Color.Black)
        12.Horizontally()
    }
}