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
import com.example.moviestream_ui_practice.utils.emailValidator
import kotlinx.android.synthetic.main.activity_movie_stream_login.etPassword
import kotlinx.android.synthetic.main.activity_movie_stream_login.tvAlreadyAccount
import kotlinx.android.synthetic.main.activity_movie_stream_sign_up.btnSignUp
import kotlinx.android.synthetic.main.activity_movie_stream_sign_up.etFullName
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
            if (isValidFields()) {
                val intent = Intent(this@MovieStreamSignUpActivity, MovieStreamLoginActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, R.string.empty_email, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isValidFields(): Boolean {
        val isEmailValid = emailValidator(etSignUpEmail.text.toString().trim()) && etSignUpEmail.text.toString().isNotEmpty()
        val isPasswordValid = etSignUpPassword.text.toString().length > 8 && etSignUpPassword.text.toString().isNotEmpty()
        val isNameValid = etFullName.text.toString().isNotEmpty()
        Toast.makeText(this, isEmailValid.toString(), Toast.LENGTH_SHORT).show()

        if (isEmailValid) {
            etSignUpEmail.error = null
        } else {
            etSignUpEmail.error = getString(R.string.wrong_email)
        }

        if (isPasswordValid) {
            etSignUpPassword.error = null
        } else {
            etSignUpPassword.error = getString(R.string.invalid_password)
        }
        return isEmailValid && isPasswordValid
    }

    private fun onClickSignUp() {
        val intent = Intent(this@MovieStreamSignUpActivity, MovieStreamLoginActivity::class.java)
        startActivity(intent)
    }
}