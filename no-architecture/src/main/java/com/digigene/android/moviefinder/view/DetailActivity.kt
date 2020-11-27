package com.digigene.android.moviefinder.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.digigene.android.moviefinder.view.Constants.DATE
import com.digigene.android.moviefinder.view.Constants.RATING
import com.digigene.android.moviefinder.view.Constants.TITLE
import com.digigene.android.moviefinder.view.Constants.YEAR
import com.digigene.android.moviefinder.R
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = intent.extras
        setContentView(R.layout.activity_detail)
        detail_rating.text = "${RATING.capitalize()}: ${bundle?.getString(RATING)}"
        detail_title.text = "${TITLE.capitalize()}: ${bundle?.getString(TITLE)}"
        detail_year.text = "${YEAR.capitalize()}: ${bundle?.getString(YEAR)}"
        detail_date.text = "${DATE.capitalize()}: ${bundle?.getString(DATE)}"
    }
}