package com.example.moviestream_ui_practice.login

import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.appcompat.app.AppCompatActivity
import com.example.moviestream_ui_practice.R
import com.example.moviestream_ui_practice.forgotpassword.MovieStreamForgotPasswordActivity
import com.example.moviestream_ui_practice.signup.MovieStreamSignUpActivity
import com.example.moviestream_ui_practice.utils.Constant
import com.example.moviestream_ui_practice.utils.colorMyText
import kotlinx.android.synthetic.main.activity_movie_stream_login.btnLogin
import kotlinx.android.synthetic.main.activity_movie_stream_login.etEmailAddress
import kotlinx.android.synthetic.main.activity_movie_stream_login.etPassword
import kotlinx.android.synthetic.main.activity_movie_stream_login.tvAlreadyAccount
import kotlinx.android.synthetic.main.activity_movie_stream_login.tvForgotPassword

class MovieStreamLoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_stream_login)
        supportActionBar?.hide()
        tvAlreadyAccount.text = colorMyText(getString(R.string.do_you_already), Constant.THIRTYTWO, Constant.FORTYTHREE, resources.getColor(R.color.primary_color)){ onClickSignIn() }
        tvAlreadyAccount.movementMethod = LinkMovementMethod.getInstance()

        tvForgotPassword.setOnClickListener {
            val intent = Intent(this@MovieStreamLoginActivity, MovieStreamForgotPasswordActivity::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {
            if (etEmailAddress.length() == Constant.ZERO || etPassword.length() == Constant.ZERO) {
                etEmailAddress.error = getString(R.string.empty_email)
                etPassword.error = getString(R.string.empty_password_error)
            } else if (etEmailAddress.text.toString().matches(Constant.EMAILPATTERN)) {
                etEmailAddress.error = getString(R.string.wrong_email)
            } else if (etPassword.length() < Constant.EIGHT) {
                etPassword.error = getString(R.string.password_length)
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
