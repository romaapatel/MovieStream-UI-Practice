package com.example.moviestream_ui_practice.login

import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.Toast
import com.example.moviestream_ui_practice.R
import com.example.moviestream_ui_practice.forgotpassword.MovieStreamForgotPasswordActivity
import com.example.moviestream_ui_practice.homescreen.MovieScreamHomeScreenActivity
import com.example.moviestream_ui_practice.signup.MovieStreamSignUpActivity
import com.example.moviestream_ui_practice.utils.Constant
import com.example.moviestream_ui_practice.utils.colorMyText
import com.example.moviestream_ui_practice.utils.webServices.Processes
import com.example.moviestream_ui_practice.utils.webServices.RequestModel
import com.example.moviestream_ui_practice.utils.webServices.RetrofitBaseActivity
import com.example.moviestream_ui_practice.utils.webServices.RetrofitResponseHandler
import kotlinx.android.synthetic.main.activity_movie_stream_login.btnLogin
import kotlinx.android.synthetic.main.activity_movie_stream_login.etEmailAddress
import kotlinx.android.synthetic.main.activity_movie_stream_login.etPassword
import kotlinx.android.synthetic.main.activity_movie_stream_login.ivPasswordToggle
import kotlinx.android.synthetic.main.activity_movie_stream_login.scrollViewLogin
import kotlinx.android.synthetic.main.activity_movie_stream_login.tvAlreadyAccount
import kotlinx.android.synthetic.main.activity_movie_stream_login.tvForgotPassword

class MovieStreamLoginActivity: RetrofitBaseActivity() {

    var hidden = false
    var loading = false

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
            if (isValidFields(etEmailAddress, etPassword, scrollViewLogin)) {
                progressBarButton(loading)
                callLoginApi()
            }
        }

        ivPasswordToggle.setOnClickListener {
            hidden = !hidden
            setupPasswordToggle(hidden, etPassword, ivPasswordToggle)
        }
    }

    private fun callLoginApi() {
        val requestModel = RequestModel()
        requestModel.apply {
            email = etEmailAddress.text.toString()
            password = etPassword.text.toString()
        }

        sendPostRequestRetrofit(requestModel, Processes.Login, scrollViewLogin, object : RetrofitResponseHandler {
            override fun onAPISuccess(response: String) {
                val intent = Intent(this@MovieStreamLoginActivity, MovieScreamHomeScreenActivity::class.java)
                startActivity(intent)
            }

            override fun onAPIFailure(message: String) {
                Toast.makeText(this@MovieStreamLoginActivity, Constant.ERRORKEY, Toast.LENGTH_SHORT).show()
            }

            override fun onRetrofitFailure(message: String) {
                Toast.makeText(this@MovieStreamLoginActivity, Constant.ERRORKEY, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun onClickSignIn() {
        val intent = Intent(this@MovieStreamLoginActivity, MovieStreamSignUpActivity::class.java)
        startActivity(intent)
    }
}