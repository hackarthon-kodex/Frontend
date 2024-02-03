
package com.app.kodex.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.kodex.R


@Composable
fun PaintDiary(
    date : String,
    paint : Int,
    text : String
){
    Column(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .wrapContentHeight()
            .background(Color.White, shape = RoundedCornerShape(12.dp))
    ){
        Text(
            text =date,
            color = Color(0xFF63605B),
            fontStyle = FontStyle(R.font.pretendard),
            modifier = Modifier.padding(top= 10.dp,start= 10.dp),
            fontWeight = FontWeight(600),
            fontSize = 16.sp
        )

        Divider(
            modifier = Modifier.padding(vertical = 10.dp),
            color = Color(0xFFE8E8E8), thickness = 1.dp
        )
        Image(
            painter = painterResource(id = paint), contentDescription = null,
            modifier = Modifier.background(color = Color.Unspecified,shape = RoundedCornerShape(12.dp))
                .height(151.dp)
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(0.9f),
                    contentScale = ContentScale.FillBounds

        )


        Text(
            text =text,
            modifier = Modifier.padding(horizontal = 22.dp, vertical = 18.dp),
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontStyle = FontStyle(R.font.gosanja),
            fontWeight = FontWeight(600),
            fontSize = 16.sp
        )

    }

}