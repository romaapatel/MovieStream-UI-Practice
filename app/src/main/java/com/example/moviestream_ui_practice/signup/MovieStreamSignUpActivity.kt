package com.example.moviestream_ui_practice.signup

import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.Toast
import com.example.moviestream_ui_practice.R
import com.example.moviestream_ui_practice.login.MovieStreamLoginActivity
import com.example.moviestream_ui_practice.utils.Constant
import com.example.moviestream_ui_practice.utils.Constant.REQUEST_POST
import com.example.moviestream_ui_practice.utils.Constant.SIGNUPAPI
import com.example.moviestream_ui_practice.utils.colorMyText
import com.example.moviestream_ui_practice.utils.webservices.ApiCallBack
import com.example.moviestream_ui_practice.utils.webservices.BaseActivity
import com.example.moviestream_ui_practice.utils.webservices.RequestModel
import kotlinx.android.synthetic.main.activity_movie_stream_sign_up.btnSignUp
import kotlinx.android.synthetic.main.activity_movie_stream_sign_up.etSignUpEmail
import kotlinx.android.synthetic.main.activity_movie_stream_sign_up.etSignUpPassword
import kotlinx.android.synthetic.main.activity_movie_stream_sign_up.tvAlreadyAccountLogin
import org.json.JSONObject

class MovieStreamSignUpActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_stream_sign_up)
        supportActionBar?.hide()
        tvAlreadyAccountLogin.text = colorMyText(getString(R.string.do_you_already_login), Constant.THIRTYTWO, Constant.FORTYONE, resources.getColor(R.color.primary_color)) { onClickSignUp() }
        tvAlreadyAccountLogin.movementMethod = LinkMovementMethod.getInstance()

        btnSignUp.setOnClickListener {
            val user = JSONObject()
            val requestModel = RequestModel()
            user.apply {
                put(Constant.EMAILKEY, etSignUpEmail.text.toString())
                put(Constant.PASSWORDKEY, etSignUpPassword.text.toString())
            }
            sendPostRequest(user, SIGNUPAPI, REQUEST_POST, object : ApiCallBack {
                override fun onSuccess() {
                   runCatching {
                       if (isValidFields(etSignUpEmail,etSignUpPassword)) {
                           val intent = Intent(this@MovieStreamSignUpActivity, MovieStreamLoginActivity::class.java)
                           startActivity(intent)
                       } else {
                           Toast.makeText(this@MovieStreamSignUpActivity, R.string.empty_email, Toast.LENGTH_SHORT).show()
                       }
                        Toast.makeText(this@MovieStreamSignUpActivity, R.string.successfully, Toast.LENGTH_LONG).show()
                    }
                }
                override fun onFailure() {
                    runCatching {
                        Toast.makeText(this@MovieStreamSignUpActivity, R.string.failure, Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }

    private fun onClickSignUp() {
        val intent = Intent(this@MovieStreamSignUpActivity, MovieStreamLoginActivity::class.java)
        startActivity(intent)
    }
}