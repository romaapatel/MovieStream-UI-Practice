package com.example.moviestream_ui_practice.forgotpassword

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviestream_ui_practice.R
import kotlinx.android.synthetic.main.activity_forgot_password.btnForgotPasswordSubmit

class MovieStreamForgotPasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        supportActionBar?.hide()

        btnForgotPasswordSubmit.setOnClickListener {
            val intent = Intent(this@MovieStreamForgotPasswordActivity, MovieStreamOTPActivity::class.java )
            startActivity(intent)
            finish()
        }
    }
}