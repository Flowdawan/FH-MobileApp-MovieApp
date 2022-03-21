package com.example.movie.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun FavoriteScreen(navController: NavController = rememberNavController()) {
    MainContent(navController = rememberNavController())
}

@Composable
fun MainContent(navController: NavController) {
    // TODO: screen
}