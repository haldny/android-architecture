package com.digigene.android.moviefinder.model.network

import com.digigene.android.moviefinder.model.entity.Movie
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MovieNetworkManager : IMovieNetworkManager {

    private lateinit var mRetrofit: Retrofit

    override fun fetchMovie(title: String): Observable<List<Movie>> {
        if (!this::mRetrofit.isInitialized) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
            mRetrofit = Retrofit.Builder().baseUrl("http://bechdeltest.com/api/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client).build()
        }
        return mRetrofit.create(MovieService::class.java).fetchMovies(title)
    }

}

interface IMovieNetworkManager {

    fun fetchMovie(title: String): Observable<List<Movie>>

}