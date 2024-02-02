package com.app.kodex

sealed class BottomMenuContent(
    val title: String,
    val route: String,
    val inactiveIcon: Int,
    val activeIcon: Int
) {
    object draw : BottomMenuContent(
        "그림 그리기",
        "home/draw",
        R.drawable.nav_draw_inactive,
        R.drawable.nav_draw_active
    )

    object chat : BottomMenuContent(
        "이야기 하기",
        "home/chat",
        R.drawable.nav_chat_inactive,
        R.drawable.nav_chat_active
    )

    object home : BottomMenuContent(
        "홈",
        "home/home",
        R.drawable.nav_home_inactive,
        R.drawable.nav_home_active
    )

    object result : BottomMenuContent(
        "일기장",
        "home/result",
        R.drawable.nvav_book_inactive,
        R.drawable.nav_book_active
    )
}