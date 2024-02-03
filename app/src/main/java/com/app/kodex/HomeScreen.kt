package com.app.kodex

import android.graphics.Paint.Align
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun HomeScreen(
    goToDraw : () -> Unit,
    goToChat : () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFAE7))
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(Color(0xFFFFF0B4), shape = RoundedCornerShape(12.dp))
        ) {
            Text(
                "고미야 놀자",
                modifier = Modifier.padding(start = 20.dp,top = 30.dp ),
                fontSize = 20.sp,
                fontStyle = FontStyle(R.font.gosanja),
                fontWeight = FontWeight(700)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .background(Color.White, shape = RoundedCornerShape(12.dp))
                        .fillMaxWidth(0.9f)
                        .height(116.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.align(Alignment.CenterStart)
                            .padding(start = 24.dp)
                    ) {

                        Text(
                            text = "오늘은\n고미랑 뭐할래?",
                            fontSize = 20.sp,
                            fontStyle = FontStyle(R.font.gosanja),
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight(700),
                            color = Color.Black
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
        }
        Column(
            modifier = Modifier
                .weight(2f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .background(Color.White,shape = RoundedCornerShape(12.dp))
                    .align(Alignment.CenterHorizontally)
                    .size(width = 308.dp, height = 280.dp)
            ) {
                Column(modifier = Modifier.align(Alignment.Center),
                    verticalArrangement = Arrangement.Center) {
                    Box(
                        modifier = Modifier
                            .shadow(elevation = 8.dp, ambientColor = Color(0.0f,0.0f,0.0f,0.06f))
                            .background(Color(0xFFFD4E4E),shape = RoundedCornerShape(12.dp))
                            .fillMaxWidth(0.9f)
                            .height(90.dp)
                            .clickable { goToDraw() }
                        ,
                        contentAlignment = Alignment.Center
                    ) {
                        Row(modifier = Modifier.align(Alignment.Center),
                            verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(id = R.drawable.home_paint),
                                tint = Color.Unspecified,
                                contentDescription = null
                            )
                            Text(
                                text = "그림 이야기할래",
                                color = Color.White,
                                fontStyle = FontStyle(R.font.pretendard),
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight(700)

                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(18.dp))
                    Box(modifier = Modifier
                        .shadow(elevation = 8.dp, ambientColor = Color(0.0f,0.0f,0.0f,0.06f))
                        .background(Color(0xFFFFB930),shape = RoundedCornerShape(12.dp))
                        .fillMaxWidth(0.9f)
                        .height(90.dp)
                        .clickable { goToChat() }
                    ) {
                        Row(modifier = Modifier.align(Alignment.Center),
                                verticalAlignment = Alignment.CenterVertically ) {
                            Icon(
                                painter = painterResource(id = R.drawable.home_chat),
                                tint = Color.Unspecified,
                                contentDescription = null
                            )
                            Text(
                                text = "이야기 할래",
                                color = Color.White,
                                fontStyle = FontStyle(R.font.pretendard),
                                fontWeight = FontWeight(700)
                            )
                        }
                    }
                }
            }
        }


    }
}