package com.example.moviestream_ui_practice.login

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.moviestream_ui_practice.R
import com.example.moviestream_ui_practice.forgotpassword.MovieStreamForgotPasswordActivity
import com.example.moviestream_ui_practice.homescreen.MovieScreamHomeScreenActivity
import com.example.moviestream_ui_practice.signup.MovieStreamSignUpActivity
import com.example.moviestream_ui_practice.utils.Constant
import com.example.moviestream_ui_practice.utils.colorMyText
import com.example.moviestream_ui_practice.utils.emailValidator
import kotlinx.android.synthetic.main.activity_movie_stream_login.btnLogin
import kotlinx.android.synthetic.main.activity_movie_stream_login.etEmailAddress
import kotlinx.android.synthetic.main.activity_movie_stream_login.etPassword
import kotlinx.android.synthetic.main.activity_movie_stream_login.tvAlreadyAccount
import kotlinx.android.synthetic.main.activity_movie_stream_login.tvForgotPassword
import kotlinx.android.synthetic.main.activity_movie_stream_sign_up.etFullName
import kotlinx.android.synthetic.main.activity_movie_stream_sign_up.etSignUpEmail
import kotlinx.android.synthetic.main.activity_movie_stream_sign_up.etSignUpPassword

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
            if (isValidFields()) {
                val intent = Intent(this@MovieStreamLoginActivity, MovieScreamHomeScreenActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, R.string.empty_email, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isValidFields(): Boolean {
        val isEmailValid = emailValidator(etEmailAddress.text.toString().trim()) && etEmailAddress.text.toString().isNotEmpty()
        val isPasswordValid = etPassword.text.toString().length > 8 && etPassword.text.toString().isNotEmpty()
        Toast.makeText(this, isEmailValid.toString(), Toast.LENGTH_SHORT).show()

        if (isEmailValid) {
            etEmailAddress.error = null
        } else {
            etEmailAddress.error = getString(R.string.wrong_email)
        }

        if (isPasswordValid) {
            etPassword.error = null
        } else {
            etPassword.error = getString(R.string.invalid_password)
        }
        return isEmailValid && isPasswordValid
    }
    private fun onClickSignIn() {
        val intent = Intent(this@MovieStreamLoginActivity, MovieStreamSignUpActivity::class.java)
        startActivity(intent)
    }
}
