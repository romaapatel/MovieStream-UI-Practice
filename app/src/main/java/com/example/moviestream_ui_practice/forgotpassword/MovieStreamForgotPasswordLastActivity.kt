package com.example.moviestream_ui_practice.forgotpassword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviestream_ui_practice.R

class MovieStreamForgotPasswordLastActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_stream_forgot_password_last)
        supportActionBar?.hide()
    }
}