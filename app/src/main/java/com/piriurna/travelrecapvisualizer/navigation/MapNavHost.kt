package com.piriurna.travelrecapvisualizer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.piriurna.travelrecapvisualizer.map.MapScreen

@Composable
fun MapNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = "map_screen"
    ) {
        composable("map_screen") {
            MapScreen()
        }
    }
}