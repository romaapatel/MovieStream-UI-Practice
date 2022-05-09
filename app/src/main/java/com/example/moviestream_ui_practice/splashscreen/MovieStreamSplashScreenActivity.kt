package com.example.moviestream_ui_practice.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.moviestream_ui_practice.R

class MovieStreamSplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_stream_splash_screen)

        supportActionBar?.hide()
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@MovieStreamSplashScreenActivity, MovieStreamOnBoardingActivity::class.java)
            startActivity(intent)
            finish()
        }, 1000)
    }
}