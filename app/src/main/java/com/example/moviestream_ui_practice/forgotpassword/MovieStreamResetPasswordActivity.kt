package com.example.moviestream_ui_practice.forgotpassword

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviestream_ui_practice.R
import kotlinx.android.synthetic.main.activity_movie_stream_reset_password.btnResetPassword

class MovieStreamResetPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_stream_reset_password)
        supportActionBar?.hide()

        btnResetPassword.setOnClickListener {
            val intent = Intent(this@MovieStreamResetPasswordActivity, MovieStreamForgotPasswordLastActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}