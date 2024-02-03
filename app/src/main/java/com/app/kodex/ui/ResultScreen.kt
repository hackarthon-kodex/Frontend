package com.app.kodex.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.kodex.R
import com.app.kodex.ResultViewModel
import com.app.kodex.ui.component.AppBar
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ResultScreen(
    viewModel: ResultViewModel = hiltViewModel()
){
    Scaffold(
        topBar = {
            AppBar(title = "나의 일기장")
        },
        containerColor = Color.White
    ) {
        val state= viewModel.getChatState
        val pages = listOf("#그림 일기","#일기")
        Column(modifier = Modifier.padding(top = it.calculateTopPadding())) {

            val pagerState= rememberPagerState()
            val coroutineScope = rememberCoroutineScope()

            TabRow(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .align(Alignment.CenterHorizontally),
                selectedTabIndex = pagerState.currentPage,
                backgroundColor = Color.White,
                contentColor = Color.Black,
                indicator = {
                    positions ->
                    TabRowDefaults.Indicator(
                        Modifier
                            .pagerTabIndicatorOffset(pagerState, tabPositions = positions)
                            .width((positions[pagerState.currentPage].width * 0.8f))
                    )
                }
            ) {
                pages.forEachIndexed{
                    index, title ->
                    Tab(selected= pagerState.currentPage==index, onClick = {
                        coroutineScope.launch {
                            pagerState.scrollToPage(index)
                        }
                    }
                    ){
                        Text(
                            text=  title,
                            fontStyle = FontStyle(R.font.pretendard),
                            fontWeight = FontWeight(600),
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                }
            }
            HorizontalPager(
                count = pages.size,
                state = pagerState
            ) {
                page -> 
                when(page){
                    0 -> PaintDiaryPage()
                    1-> TexDiaryPage(text = state.value.result)
                }
            }



        }
    }
}