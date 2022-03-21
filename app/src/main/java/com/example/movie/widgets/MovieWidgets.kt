package com.example.movie.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.movie.models.Movie
import com.example.movie.models.getMovies

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MovieRow(movie: Movie = getMovies()[0],
             onItemClick: (String) -> Unit = {}) {
    var isArrowUp by remember {
        mutableStateOf(false)
    }
    Card(
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
            .padding(4.dp)
            .clickable { onItemClick(movie.id) },
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
                        Divider(
                            color = Color.Gray,
                            modifier = Modifier
                                .padding(2.dp)
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