package com.example.moviestream_ui_practice.login

import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.appcompat.app.AppCompatActivity
import com.example.moviestream_ui_practice.R
import com.example.moviestream_ui_practice.signup.MovieStreamSignUpActivity
import com.example.moviestream_ui_practice.splashscreen.MovieStreamOnBoardingActivity
import com.example.moviestream_ui_practice.utils.Constant
import com.example.moviestream_ui_practice.utils.colorMyText
import kotlinx.android.synthetic.main.activity_movie_stream_login.btnLogin
import kotlinx.android.synthetic.main.activity_movie_stream_login.etEmailAddress
import kotlinx.android.synthetic.main.activity_movie_stream_login.etPassword
import kotlinx.android.synthetic.main.activity_movie_stream_login.tvAlreadyAccount

class MovieStreamLoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_stream_login)
        supportActionBar?.hide()
        tvAlreadyAccount.text = colorMyText(getString(R.string.do_you_already_have_account), Constant.THIRTYTWO, Constant.FORTYTHREE, resources.getColor(R.color.primary_color)) { onClickSignIn() }
        tvAlreadyAccount.movementMethod = LinkMovementMethod.getInstance()

        btnLogin.setOnClickListener {
            if (etEmailAddress.text.toString().isEmpty() || etPassword.text.toString().isEmpty()) {
                etEmailAddress.error = getString(R.string.validation_empty_email)
                etPassword.error = getString(R.string.validation_empty_password_error)
            } else if (etPassword.length() < Constant.EIGHT) {
                etPassword.error = getString(R.string.validation_password_length)
            } else {
                etEmailAddress.error = null
                etPassword.error = null
                val intent = Intent(this@MovieStreamLoginActivity, MovieStreamSignUpActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun onClickSignIn() {
        val intent = Intent(this@MovieStreamLoginActivity, MovieStreamSignUpActivity::class.java)
        startActivity(intent)
    }
}
