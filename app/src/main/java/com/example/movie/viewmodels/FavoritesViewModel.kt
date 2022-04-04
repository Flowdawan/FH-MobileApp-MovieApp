package com.example.movie.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.movie.models.Movie


class FavoritesViewModel: ViewModel() {
    private val _favoriteMovies = mutableStateListOf<Movie>()

    val favoriteMovies: List<Movie>
        get() = _favoriteMovies

    fun addMovie(movie: Movie) {
        if(!isMovieInList(movie)){
            _favoriteMovies.add(movie)
        }
    }

    fun removeMovie(movie: Movie) {
        _favoriteMovies.remove(movie)
    }

    fun getAllMovies(): List<Movie> {
        return _favoriteMovies
    }

    fun isMovieInList(movie: Movie): Boolean {
        return _favoriteMovies.contains(movie)
    }

    fun getOneMovie(movie: Movie): Movie? {
        return _favoriteMovies.find { it.id == movie.id }
    }
}

