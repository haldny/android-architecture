package com.digigene.android.moviefinder.model.network

import com.digigene.android.moviefinder.model.entity.Movie
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("getMoviesByTitle")
    fun fetchMovies(@Query("title") title: String): Observable<List<Movie>>
}