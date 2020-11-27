package com.digigene.android.moviefinder.controller

import com.digigene.android.moviefinder.model.entity.Movie
import com.digigene.android.moviefinder.model.network.IMovieNetworkManager
import com.digigene.android.moviefinder.model.network.MovieNetworkManager
import com.digigene.android.moviefinder.wrapper.SchedulersWrapper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import retrofit2.HttpException

class MovieController() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val schedulersWrapper: SchedulersWrapper = SchedulersWrapper()
    private lateinit var viewContract: ViewContract
    private lateinit var movieManager: IMovieNetworkManager

    constructor(view: ViewContract,
                movieManager: IMovieNetworkManager = MovieNetworkManager()) : this() {
        this.viewContract = view
        this.movieManager = movieManager
    }

    fun fetchMovies(title: String) {

        val disposable: Disposable = movieManager.fetchMovie(title)
                .subscribeOn(schedulersWrapper.io())
                .observeOn(schedulersWrapper.ui())
                .subscribeWith(object : DisposableObserver<List<Movie>?>() {

            override fun onNext(movies: List<Movie>) {
                viewContract.hideProgressBar()
                viewContract.updateMovies(movies)
            }

            override fun onStart() {
                viewContract.showProgressBar()
            }

            override fun onComplete() {
            }

            override fun onError(e: Throwable) {
                when (e) {
                    is HttpException -> viewContract.onError("${e.message} - ${e.response().errorBody()?.string()}")
                    else -> viewContract.onError(e.message)
                }
            }
        })

        compositeDisposable.add(disposable)
    }

    fun onStop() {
        compositeDisposable.clear()
    }

}