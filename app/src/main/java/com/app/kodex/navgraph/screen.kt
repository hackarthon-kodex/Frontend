package com.app.kodex.navgraph

const val ROOT_ROUTE = "root"
const val HOME_ROUTE = "main/home"
const val DRAW_ROUTE = "main/draw"
const val CHAT_ROUTE = "main/chat"
const val RESULT_ROUTE = "main/result"

sealed class HomeScreen(val route : String){
    object Home : HomeScreen(route = "home/home")
}

sealed class DrawScreen(val route : String){
    object Home : DrawScreen(route = "home/draw")
}

sealed class ChatScreen(val route : String){
    object Home : DrawScreen(route = "home/chat")
}

sealed class ResultScreen(val route : String){
    object Home : DrawScreen(route = "home/result")
}