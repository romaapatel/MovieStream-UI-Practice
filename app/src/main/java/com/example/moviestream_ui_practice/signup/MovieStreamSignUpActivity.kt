package com.example.moviestream_ui_practice.signup

import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.Toast
import com.example.moviestream_ui_practice.R
import com.example.moviestream_ui_practice.homescreen.MovieScreamHomeScreenActivity
import com.example.moviestream_ui_practice.login.MovieStreamLoginActivity
import com.example.moviestream_ui_practice.utils.Constant
import com.example.moviestream_ui_practice.utils.colorMyText
import com.example.moviestream_ui_practice.utils.webServices.Processes
import com.example.moviestream_ui_practice.utils.webServices.RequestModel
import com.example.moviestream_ui_practice.utils.webServices.RetrofitBaseActivity
import com.example.moviestream_ui_practice.utils.webServices.RetrofitResponseHandler
import kotlinx.android.synthetic.main.activity_movie_stream_sign_up.btnSignUp
import kotlinx.android.synthetic.main.activity_movie_stream_sign_up.etSignUpEmail
import kotlinx.android.synthetic.main.activity_movie_stream_sign_up.etSignUpPassword
import kotlinx.android.synthetic.main.activity_movie_stream_sign_up.ivSignUpPasswordToggle
import kotlinx.android.synthetic.main.activity_movie_stream_sign_up.scrollViewSignUp
import kotlinx.android.synthetic.main.activity_movie_stream_sign_up.tvAlreadyAccountLogin

class MovieStreamSignUpActivity : RetrofitBaseActivity() {

    var hidden = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_stream_sign_up)
        supportActionBar?.hide()
        tvAlreadyAccountLogin.text = colorMyText(getString(R.string.do_you_already_login), Constant.THIRTYTWO, Constant.FORTYONE, resources.getColor(R.color.primary_color)) { onClickSignUp() }
        tvAlreadyAccountLogin.movementMethod = LinkMovementMethod.getInstance()

        btnSignUp.setOnClickListener {
            if (isValidFields(etSignUpEmail, etSignUpPassword, scrollViewSignUp)) {
                startLoading()
                callRegisterApi()
            }
        }

        ivSignUpPasswordToggle.setOnClickListener {
            hidden = !hidden
            setupPasswordToggle(hidden, etSignUpPassword, ivSignUpPasswordToggle)
        }
    }

    private fun callRegisterApi() {
        val requestModel = RequestModel()
        requestModel.apply {
            email = etSignUpEmail.text.toString()
            password = etSignUpPassword.text.toString()
        }
        sendPostRequestRetrofit(requestModel, Processes.Register, scrollViewSignUp ,object : RetrofitResponseHandler {
            override fun onAPISuccess(response: String) {
                endLoading()
                startActivity(Intent(this@MovieStreamSignUpActivity, MovieScreamHomeScreenActivity::class.java))
                finish()
            }

            override fun onAPIFailure(message: String) {
                Toast.makeText(this@MovieStreamSignUpActivity, Constant.ERRORKEY, Toast.LENGTH_SHORT).show()
                endLoading()
            }

            override fun onRetrofitFailure(message: String) {
              endLoading()
            }
        })
    }

    private fun onClickSignUp() {
        val intent = Intent(this@MovieStreamSignUpActivity, MovieStreamLoginActivity::class.java)
        startActivity(intent)
    }
}