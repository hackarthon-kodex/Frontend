package com.app.kodex.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.kodex.R


@Composable
fun IconBox(
    title : String,
){
    Box(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(116.dp)
            .background(color = Color(0xFFFFB930), shape = RoundedCornerShape(12.dp))
    ){
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.align(Alignment.CenterStart)
                .padding(start = 24.dp)
        ) {

            Text(
                text = title,
                fontSize = 20.sp,
                fontStyle = FontStyle(R.font.gosanja),
                textAlign = TextAlign.Start,
                fontWeight = FontWeight(700),
                color = Color.White
            )
            Spacer(modifier = Modifier.height(4.dp))
            Icon(painter = painterResource(id = R.drawable.icon_yellow),tint= Color.Unspecified, contentDescription = null)

        }

        Image(
            painter = painterResource(id = R.drawable.objects),
            contentDescription = null,
            modifier = Modifier
                .height(140.dp)
                .width(140.dp)
                .align(Alignment.BottomEnd)

        )
    }
}