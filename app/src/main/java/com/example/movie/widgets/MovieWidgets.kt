package com.example.movie.widgets

import android.widget.HorizontalScrollView
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movie.R
import com.example.movie.models.Movie
import com.example.movie.models.getMovies

@Preview
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
                    .size(110.dp)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movie.images[0])
                        .crossfade(true)
                        .build(),
                    contentDescription = "Movie Cober",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.clip(CircleShape)
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

@Composable
fun HorizontalScrollableImageView(movie: Movie = getMovies()[0]){
    LazyRow{
        items(movie.images){ movie ->
            Card(
                modifier = Modifier.padding(12.dp).size(240.dp),
                elevation = 4.dp
            )
            {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movie)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Movie Cover",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.clip(RectangleShape)
                )
            }
        }
    }

}