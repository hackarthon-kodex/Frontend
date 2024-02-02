package com.app.kodex.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
@Composable
fun MainNavGraph(
    innerNavController: NavHostController,

) {
    NavHost(navController = innerNavController, startDestination = HOME_ROUTE, route = ROOT_ROUTE)
    {
        HomeNavGraph(innerNavController)
        DrawNavGraph(innerNavController)
        ChatNavGraph(innerNavController)
        ResultNavGraph(innerNavController)
    }
}