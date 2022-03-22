package com.example.movie.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movie.models.Movie
import com.example.movie.models.getMovies
import com.example.movie.navigation.MovieScreens
import com.example.movie.screens.detail.filterMovie
import com.example.movie.widgets.MovieRow

@Composable
fun FavoriteScreen(navController: NavController = rememberNavController()) {
    Scaffold(
        topBar = {
            TopAppBar {
                Row {
                    Icon(imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Arrow back",
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        })
                    Spacer(modifier = Modifier.width(20.dp))

                    Text(text = "Favorites")
                }
            }
        }
    ) {
        MainContent(navController = navController)
    }
}

@Composable
fun MainContent(navController: NavController) {
    val favoriteMovies = listOf<String>("tt0903747", "tt0848228", "tt0944947")
    LazyColumn {
        items(items = favoriteMovies) { movie ->
            MovieRow(movie = filterMovie(movie)){ movieId ->
                navController.navigate(route =  MovieScreens.DetailScreen.name + "/$movieId")
            }
        }
    }
}
