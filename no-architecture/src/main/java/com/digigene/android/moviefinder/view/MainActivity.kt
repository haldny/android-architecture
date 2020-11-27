package com.digigene.android.moviefinder.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.digigene.android.moviefinder.R
import com.digigene.android.moviefinder.controller.MovieController
import com.digigene.android.moviefinder.controller.ViewContract
import com.digigene.android.moviefinder.view.Constants.DATE
import com.digigene.android.moviefinder.view.Constants.RATING
import com.digigene.android.moviefinder.view.Constants.TITLE
import com.digigene.android.moviefinder.view.Constants.YEAR
import com.digigene.android.moviefinder.model.entity.Movie
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ViewContract {

    private lateinit var moviesAdapter: MoviesAdapter

    private val movieController = MovieController(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        moviesAdapter = MoviesAdapter(onClick = { item ->
            var bundle = Bundle()
            bundle.putString(RATING, item.rating)
            bundle.putString(TITLE, item.title)
            bundle.putString(YEAR, item.year)
            bundle.putString(DATE, item.date)
            var intent = Intent(this, DetailActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        })
        main_activity_recyclerView.adapter = moviesAdapter
        respondToClicks()
    }

    private fun respondToClicks() {
        main_activity_button.setOnClickListener {
            movieController.fetchMovies(main_activity_editText.text.toString())
        }
    }

    override fun onResume() {
        super.onResume()
        hideProgressBar()
    }

    override fun onError(message: String?) {
        main_activity_progress_bar.visibility = View.GONE
        Toast.makeText(this@MainActivity, "Error retrieving data: ${message}", Toast.LENGTH_SHORT)
    }

    override fun showProgressBar() {
        main_activity_progress_bar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        main_activity_progress_bar.visibility = View.GONE
    }

    override fun onStop() {
        super.onStop()
        movieController.onStop()
    }

    override fun updateMovies(movies: List<Movie>) {
        moviesAdapter.updateList(movies)
        moviesAdapter.notifyDataSetChanged()
    }

}
