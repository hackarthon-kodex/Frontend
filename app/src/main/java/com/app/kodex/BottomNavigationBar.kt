package com.app.kodex

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
) {
    val home_screens = listOf(
        BottomMenuContent.home,
        BottomMenuContent.draw,
        BottomMenuContent.chat,
        BottomMenuContent.result
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val bottomBarDestination = home_screens.any { it.route == currentDestination?.route }


    if (bottomBarDestination) {
        BottomNavigation(
            backgroundColor = Color.White,
        ) {
            home_screens.forEach { screen ->
                val selected = currentDestination?.hierarchy?.any{
                    it.route == screen.route
                } == true

                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController,
                    selected = selected,
                )
            }
        }
    }

}

@Composable
fun RowScope.AddItem(
    screen : BottomMenuContent,
    currentDestination: NavDestination?,
    navController: NavHostController,
    selected: Boolean,
) {

    BottomNavigationItem(
        selected = selected,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
        icon = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if (selected) {
                    Icon(
                        painter = painterResource(id = screen.activeIcon),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )

                    Text(
                        text = screen.title,
                        color = Color(0xFFF8A90D),
                        fontStyle = FontStyle(R.font.pretendard),
                        fontWeight = FontWeight(500)
                    )

                } else {
                    Icon(
                        painter = painterResource(id = screen.inactiveIcon),
                        contentDescription = null,
                        tint = Color(0xFF9E9E9E)
                    )

                    Text(
                        text = screen.title,
                        color = Color(0xFF9E9E9E),
                        fontStyle = FontStyle(R.font.pretendard),
                        fontWeight = FontWeight(500)

                    )
                }
            }
        })
}





