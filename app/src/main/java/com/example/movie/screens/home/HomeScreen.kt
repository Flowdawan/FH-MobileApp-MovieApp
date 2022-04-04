package com.example.movie.screens.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movie.models.Movie
import com.example.movie.models.getMovies
import com.example.movie.navigation.MovieScreens
import com.example.movie.viewmodels.FavoritesViewModel
import com.example.movie.widgets.MovieRow

@Composable
fun HomeScreen(
    navController: NavController = rememberNavController(),
    favoritesViewModel: FavoritesViewModel = viewModel()
) {
    var showMenu by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "HomeScreen") },
                actions = {
                    IconButton(onClick = { showMenu = !showMenu }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More")
                    }

                    DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                        DropdownMenuItem(onClick = { navController.navigate(MovieScreens.FavoriteScreen.name) }) {
                            Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = "Favorites"
                            )
                        }
                    }
                }
            )
        }
    ) {
        MainContent(navController = navController, favoritesViewModel = favoritesViewModel)
    }
}

@Composable
fun MainContent(
    navController: NavController,
    movies: List<Movie> = getMovies(),
    favoritesViewModel: FavoritesViewModel
) {
    LazyColumn {
        items(items = movies) { movie ->
            MovieRow(
                movie = movie,
                onFavoriteClick = { movie ->
                    if (favoritesViewModel.isMovieInList(movie)) {
                        favoritesViewModel.removeMovie(movie)

                    } else {
                        favoritesViewModel.addMovie(movie)
                    }
                },
                onItemClick = { movieId ->
                    navController.navigate(route = MovieScreens.DetailScreen.name + "/$movieId")
                },
                isAlreadyInList = favoritesViewModel.isMovieInList(movie)
            )
        }
    }
}
