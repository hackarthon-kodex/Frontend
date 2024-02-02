
package com.app.kodex.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.kodex.R


@Composable
fun TextDiary(
    date : String,
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