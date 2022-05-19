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
import com.example.moviestream_ui_practice.utils.webservices.ApiCallBack
import com.example.moviestream_ui_practice.utils.webservices.BaseActivity
import com.example.moviestream_ui_practice.utils.webservices.RequestModel
import kotlinx.android.synthetic.main.activity_movie_stream_login.btnLogin
import kotlinx.android.synthetic.main.activity_movie_stream_login.etEmailAddress
import kotlinx.android.synthetic.main.activity_movie_stream_login.etPassword
import kotlinx.android.synthetic.main.activity_movie_stream_login.tvAlreadyAccount
import kotlinx.android.synthetic.main.activity_movie_stream_login.tvForgotPassword
import org.json.JSONObject

class MovieStreamLoginActivity : BaseActivity() {

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
            val user = JSONObject()
            val requestModel = RequestModel()
            user.apply {
                put(Constant.EMAILKEY, etEmailAddress.text.toString())
                put(Constant.PASSWORDKEY, etPassword.text.toString())
            }
            sendPostRequest(user, Constant.SIGNINAPI, Constant.REQUEST_POST, object : ApiCallBack {
                override fun onSuccess() {
                   runCatching {
                       if (isValidFields(etEmailAddress, etPassword)) {
                           val intent = Intent(this@MovieStreamLoginActivity, MovieScreamHomeScreenActivity::class.java)
                           startActivity(intent)
                       } else {
                           Toast.makeText(this@MovieStreamLoginActivity, R.string.empty_email, Toast.LENGTH_SHORT).show()
                       }
                    }
                }
                override fun onFailure() {
                   runCatching {
                        Toast.makeText(this@MovieStreamLoginActivity, R.string.failure, Toast.LENGTH_LONG).show()
                    }
                }
            })

        }
    }

    private fun onClickSignIn() {
        val intent = Intent(this@MovieStreamLoginActivity, MovieStreamSignUpActivity::class.java)
        startActivity(intent)
    }
}
