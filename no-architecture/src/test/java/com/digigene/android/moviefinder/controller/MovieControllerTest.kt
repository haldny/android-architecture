package com.digigene.android.moviefinder.controller

import com.digigene.android.moviefinder.model.entity.Movie
import com.digigene.android.moviefinder.model.network.IMovieNetworkManager
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.HttpException
import retrofit2.Response

class MovieControllerTest {

    @Mock
    private lateinit var viewContract: ViewContract

    @Mock
    private lateinit var movieNetworkManager: IMovieNetworkManager

    @InjectMocks
    private lateinit var movieController: MovieController

    companion object {
        val EXPECTED_MOVIES = listOf<Movie>(
                Movie("haldny1", "4.5", "30/11/2020", "2020"),
                Movie("haldny2", "3.5", "12/11/1991", "1991")
        )
    }

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun fetchMovies() {
        val successResult = Single.just(EXPECTED_MOVIES).toObservable()
        doReturn(successResult).`when`(movieNetworkManager).fetchMovie("haldny")
        movieController.fetchMovies("haldny")

        verify(viewContract).hideProgressBar()
        verify(viewContract).updateMovies(EXPECTED_MOVIES)
    }

    @Test
    fun fetchMoviesError() {
        val errorResult = Single.error<HttpException>(HttpException(getResponseError())).toObservable()

        doReturn(errorResult).`when`(movieNetworkManager).fetchMovie("eduardo")
        movieController.fetchMovies("eduardo")

        verify(viewContract).onError("HTTP 404 Response.error() - Resource not found!")
    }

    private fun getResponseError() : Response<HttpException> {
        return Response.error(404,
                ResponseBody.create(
                        MediaType.parse("application/json"),
                        "Resource not found!"))
    }

}