package com.app.kodex.navgraph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.app.kodex.DrawScreen

fun NavGraphBuilder.DrawNavGraph(
    navController: NavController,
) {
    navigation(startDestination = DrawScreen.Home.route, route = DRAW_ROUTE) {
        composable(DrawScreen.Home.route) {
            DrawScreen()
        }


    }

}