package com.example.movie.screens.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movie.models.Movie
import com.example.movie.models.getMovies
import com.example.movie.widgets.MovieRow


@Preview(showBackground = true)
@Composable
fun DetailScreen(
    navController: NavController = rememberNavController(),
    movieId: String? = getMovies()[0].id
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
        MainContent(movie) {
        }
    }

}

@Composable
fun MainContent(movie: Movie, content: @Composable () -> Unit) {
    MovieRow(movie)
}

fun filterMovie(movieId: String?): Movie {
    return getMovies().filter { movie -> movie.id == movieId }[0]
}