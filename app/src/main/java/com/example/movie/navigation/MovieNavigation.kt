package com.example.movie.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movie.screens.FavoriteScreen
import com.example.movie.screens.detail.DetailScreen
import com.example.movie.screens.home.HomeScreen

@Composable
fun MovieNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = MovieScreens.HomeScreen.name) {

        composable(MovieScreens.HomeScreen.name) { HomeScreen(navController = navController) }

        composable(
            MovieScreens.DetailScreen.name + "/{movieId}",
            arguments = listOf(
                navArgument("movieId") {
                    type = NavType.StringType
                })
        ) { backStackEntry ->
            DetailScreen(navController = navController, movieId = backStackEntry.arguments?.getString("movieId"))
        }

        composable(MovieScreens.FavoriteScreen.name) { FavoriteScreen(navController = navController) }

    }
}