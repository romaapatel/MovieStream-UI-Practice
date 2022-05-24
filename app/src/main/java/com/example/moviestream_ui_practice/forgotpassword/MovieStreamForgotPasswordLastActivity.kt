package com.example.moviestream_ui_practice.forgotpassword

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviestream_ui_practice.R
import com.example.moviestream_ui_practice.homescreen.MovieScreamHomeScreenActivity
import kotlinx.android.synthetic.main.activity_movie_stream_forgot_password_last.btnResetLogin

class MovieStreamForgotPasswordLastActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_stream_forgot_password_last)
        supportActionBar?.hide()

        btnResetLogin.setOnClickListener {
            startActivity(Intent(this@MovieStreamForgotPasswordLastActivity, MovieScreamHomeScreenActivity::class.java))
        }
    }
}