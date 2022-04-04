package com.example.movie.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movie.screens.favorite.FavoriteScreen
import com.example.movie.screens.detail.DetailScreen
import com.example.movie.screens.home.HomeScreen
import com.example.movie.viewmodels.FavoritesViewModel

@Composable
fun MovieNavigation() {
    val navController = rememberNavController()
    val favoritesViewModel: FavoritesViewModel = viewModel()

    NavHost(navController = navController, startDestination = MovieScreens.HomeScreen.name) {

        composable(MovieScreens.HomeScreen.name) { HomeScreen(navController = navController, favoritesViewModel = favoritesViewModel) }

        composable(
            MovieScreens.DetailScreen.name + "/{movieId}",
            arguments = listOf(
                navArgument("movieId") {
                    type = NavType.StringType
                })
        ) { backStackEntry ->
            DetailScreen(navController = navController, movieId = backStackEntry.arguments?.getString("movieId"), favoritesViewModel = favoritesViewModel)
        }

        composable(MovieScreens.FavoriteScreen.name) { FavoriteScreen(navController = navController, favoritesViewModel = favoritesViewModel) }

    }
}