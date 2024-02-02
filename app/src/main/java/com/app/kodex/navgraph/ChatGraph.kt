package com.app.kodex.navgraph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.app.kodex.ChatScreen

fun NavGraphBuilder.ChatNavGraph(
    navController: NavController,
) {
    navigation(startDestination = ChatScreen.Home.route, route =CHAT_ROUTE) {
        composable(ChatScreen.Home.route) {
            ChatScreen()

        }


    }

}