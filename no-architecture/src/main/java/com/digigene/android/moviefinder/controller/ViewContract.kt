package com.digigene.android.moviefinder.controller

import com.digigene.android.moviefinder.model.entity.Movie

interface ViewContract {

    fun hideProgressBar()
    fun showProgressBar()
    fun updateMovies(movies: List<Movie>)
    fun onError(message: String?)

}