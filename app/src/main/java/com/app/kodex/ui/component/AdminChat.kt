package com.app.kodex.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.kodex.R

@Composable
fun AdminChat(
    text : String
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(painter = painterResource(id = R.drawable.profile),tint = Color.Unspecified, contentDescription = null)
        Spacer(modifier = Modifier.width(8.dp))
        Box(
            modifier = Modifier
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(12.dp)
                )
                .widthIn(max = LocalConfiguration.current.screenWidthDp.dp * 0.8f)
        ) {
            Text(
                text = text,
                fontStyle= FontStyle(R.font.pretendard),
                color = Color(0xFF212121),
                fontSize = 17.sp,
                fontWeight = FontWeight(500),
                modifier = Modifier.padding(vertical = 10.dp, horizontal = 8.dp)
            )
        }
    }
}