package com.app.kodex.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.kodex.R

@Composable
fun AppBar(
    title : String
) {

    Row(
        modifier  = Modifier.fillMaxWidth()
            .wrapContentHeight()
        ,
        horizontalArrangement = Arrangement.Center
    ){
        Text(text= title, fontStyle = FontStyle(R.font.gosanja), fontSize = 20.sp,modifier = Modifier.padding(15.dp))
    }
}