package com.example.movie.screens.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movie.models.Movie
import com.example.movie.models.getMovies
import com.example.movie.navigation.MovieScreens
import com.example.movie.viewmodels.FavoritesViewModel
import com.example.movie.widgets.FavoriteIcon
import com.example.movie.widgets.HorizontalScrollableImageView
import com.example.movie.widgets.MovieRow


@Preview(showBackground = true)
@Composable
fun DetailScreen(
    navController: NavController = rememberNavController(),
    movieId: String? = getMovies()[0].id,
    favoritesViewModel: FavoritesViewModel = viewModel()
) {
    val movie = filterMovie(movieId)
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

                    Text(text = movie.title)
                }
            }
        }
    ) {
        MainContent(movie, favoritesViewModel = favoritesViewModel)
    }

}

@Composable
fun MainContent(movie: Movie, favoritesViewModel: FavoritesViewModel) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MovieRow(movie = movie)
        {
            FavoriteIcon(
                movie = movie,
                isAlreadyInList = favoritesViewModel.isMovieInList(movie),
                onFavoriteClick = { movie ->
                    if (favoritesViewModel.isMovieInList(movie)) {
                        favoritesViewModel.removeMovie(movie)

                    } else {
                        favoritesViewModel.addMovie(movie)
                    }
                }

            )
        }
    }

    Divider(
        color = Color.Gray,
        modifier = Modifier
            .padding(2.dp)
            .alpha(alpha = 0.6F)
    )

    Text(
        "Movie Images",
        style = MaterialTheme.typography.h5
    )

    HorizontalScrollableImageView(movie)
}

fun filterMovie(movieId: String?): Movie {
    return getMovies().filter { movie -> movie.id == movieId }[0]
}