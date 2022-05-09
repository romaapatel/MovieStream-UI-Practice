package com.example.moviestream_ui_practice.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.example.moviestream_ui_practice.R
import com.example.moviestream_ui_practice.login.MovieStreamLoginActivity
import com.example.moviestream_ui_practice.utils.Constant
import com.example.moviestream_ui_practice.utils.colorMyText
import kotlinx.android.synthetic.main.activity_movie_stream_login.etPassword
import kotlinx.android.synthetic.main.activity_movie_stream_login.tvAlreadyAccount
import kotlinx.android.synthetic.main.activity_movie_stream_sign_up.btnSignUp
import kotlinx.android.synthetic.main.activity_movie_stream_sign_up.etSignUpEmail
import kotlinx.android.synthetic.main.activity_movie_stream_sign_up.etSignUpPassword
import kotlinx.android.synthetic.main.activity_movie_stream_sign_up.tvAlreadyAccountLogin

class MovieStreamSignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_stream_sign_up)
        supportActionBar?.hide()
        tvAlreadyAccountLogin.text = colorMyText(
            getString(R.string.do_you_already_login),
            Constant.THIRTYTWO,
            Constant.FORTYONE,
            resources.getColor(R.color.primary_color)
        ) { onClickSignUp() }
        tvAlreadyAccountLogin.movementMethod = LinkMovementMethod.getInstance()

        btnSignUp.setOnClickListener {
            if(isValidFields()) {
                val intent =
                    Intent(this@MovieStreamSignUpActivity, MovieStreamLoginActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Signed In", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isValidFields(): Boolean {
        val isEmailValid = etSignUpEmail.text.toString().trim().matches(Constant.EMAILPATTERN)
        val isPasswordValid = etSignUpPassword.text.toString().length > 8
        Toast.makeText(this, isEmailValid.toString(), Toast.LENGTH_SHORT).show()

        if(isEmailValid) {
            etSignUpEmail.error = null
        } else {
            if (etSignUpEmail.length() == 0 ) {
                etSignUpEmail.error = getString(R.string.empty_email)
            } else {
                etSignUpEmail.error = getString(R.string.wrong_email)
            }
        }

        if(isPasswordValid) {
            etSignUpPassword.error = null
        } else {
            if (etSignUpPassword.length() == 0) {
                etSignUpPassword.error = getString(R.string.empty_password_error)
                if(etSignUpPassword.length() < 8 ) {
                    etSignUpPassword.error = getString(R.string.password_length)
                } else {
                    etSignUpPassword.error = getString(R.string.invalid_password)
                }
            }
        }
        return isEmailValid && isPasswordValid
    }

    private fun onClickSignUp() {
        val intent = Intent(this@MovieStreamSignUpActivity, MovieStreamLoginActivity::class.java)
        startActivity(intent)
    }
}