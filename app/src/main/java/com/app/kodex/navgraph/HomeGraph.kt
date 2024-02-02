package com.app.kodex.navgraph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.app.kodex.HomeScreen

fun NavGraphBuilder.HomeNavGraph(
    navController: NavController,
) {
    navigation(startDestination = HomeScreen.Home.route, route = HOME_ROUTE) {
        composable(HomeScreen.Home.route) {
            HomeScreen(
                goToDraw= {
                    navController.navigate(DrawScreen.Home.route)
                },
                goToChat= {
                    navController.navigate(ChatScreen.Home.route)
                }
            )
        }


    }

}