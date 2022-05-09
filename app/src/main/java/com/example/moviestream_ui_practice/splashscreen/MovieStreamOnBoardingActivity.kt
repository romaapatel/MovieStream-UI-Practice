package com.example.moviestream_ui_practice.splashscreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moviestream_ui_practice.R
import com.example.moviestream_ui_practice.utils.Constant
import com.example.moviestream_ui_practice.utils.colorMyText
import kotlinx.android.synthetic.main.activity_movie_stream_on_boarding.tvMainTitle

class MovieStreamOnBoardingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_stream_on_boarding)
        supportActionBar?.hide()
        tvMainTitle.text = colorMyText(getString(R.string.movie_start), Constant.FOUR, Constant.NINE, resources.getColor(R.color.primary_color)) {onClickMovieStart()}
    }

    private fun onClickMovieStart() {
        TODO("Not yet implemented")
    }
}