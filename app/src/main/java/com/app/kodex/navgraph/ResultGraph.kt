package com.app.kodex.navgraph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.app.kodex.ui.ResultScreen

fun NavGraphBuilder.ResultNavGraph(
    navController: NavController,
) {
    navigation(startDestination = ResultScreen.Home.route, route =RESULT_ROUTE) {
        composable(ResultScreen.Home.route) {
            ResultScreen()
        }


    }

}