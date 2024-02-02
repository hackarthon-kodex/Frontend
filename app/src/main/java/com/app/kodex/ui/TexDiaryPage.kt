package com.app.kodex.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.app.kodex.data.DiaryText
import com.app.kodex.ui.component.TextDiary

@Composable
fun TexDiaryPage()
{
    val texts = listOf<DiaryText>(DiaryText("2023년 1월 14일 목요일","오늘은 나무를 파고 놀았다. 나무에서 벌레를 보고 도망갔다. 엄마가 없어서 너무 재미있진 않았다. 그래도 내일은\n" +
            "친구랑 놀고 싶다. 오늘은 비가 안와서 좋았다. 참 재미있었다 !"),
        DiaryText("2023년 1월 14일 목요일","오늘은 나무를 팠다. 나무에서 벌레를 보고 도망갔다. 엄마가 없어서 너무 재미있게 놀았다. 그래도 내일은 친구랑 놀고 싶다"))
    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFAE7)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Spacer(modifier = Modifier.height(20.dp))

        }
        itemsIndexed(texts){
            index, item ->
            TextDiary(
                date = item.date,
                text = item.text
            )
            Spacer(modifier = Modifier.height(32.dp))
        }
    }

}