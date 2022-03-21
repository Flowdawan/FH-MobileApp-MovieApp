package com.example.movie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movie.models.Movie
import com.example.movie.models.getMovies
import com.example.movie.ui.theme.MovieTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApp {
                MainContent()
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {

    var showMenu by remember {
        mutableStateOf(false)
    }

    MovieTheme {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text(text = "HomeScreen") },
                    actions = {
                        IconButton(onClick = { showMenu = !showMenu }) {
                            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More")
                        }

                        DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                            DropdownMenuItem(onClick = { /* TODO: */ }) {
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
            content()
        }
    }
}


@Composable
fun MainContent() {
    renderMovieList(getMovies())
}

@Composable
fun renderMovieList(movieList: List<Movie>) {
    LazyColumn {
        items(movieList) { movie ->
            MovieRow(movie)
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MovieRow(movie: Movie) {
    var isArrowUp by remember {
        mutableStateOf(false)
    }
    Card(
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
            .padding(4.dp),
        elevation = 6.dp,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .size(110.dp),
                elevation = 6.dp,
                ) {
                Icon(
                    imageVector = Icons.Default.AccountBox, contentDescription = "Account box",
                )
            }
            Column() {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.caption,
                )
                Text(
                    text = "Director: ${movie.director}",
                    style = MaterialTheme.typography.subtitle1,
                )
                Text(
                    text = "Released: ${movie.year}",
                    style = MaterialTheme.typography.subtitle1,
                )
                AnimatedVisibility(visible = isArrowUp) {
                    Column(
                        modifier = Modifier.padding(7.dp)
                    ) {
                        Text(
                            text = "Plot: ${movie.plot}",
                            style = MaterialTheme.typography.subtitle1,
                        )
                        Divider(color = Color.Gray,
                            modifier = Modifier.padding(2.dp)
                                .alpha(alpha = 0.6F)
                            )
                        Text(
                            text = "Genre: ${movie.genre}",
                            style = MaterialTheme.typography.subtitle1,
                        )
                        Text(
                            text = "Actors: ${movie.actors}",
                            style = MaterialTheme.typography.subtitle1,
                        )
                        Text(
                            text = "Rating: ${movie.rating}",
                            style = MaterialTheme.typography.subtitle1,
                        )
                    }
                }
                Icon(imageVector = if (isArrowUp) {
                    Icons.Default.KeyboardArrowDown
                } else {
                    Icons.Default.KeyboardArrowUp
                },
                    contentDescription = "Arrow",
                    modifier = Modifier.clickable { isArrowUp = !isArrowUp }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp {
        MainContent()
    }
}