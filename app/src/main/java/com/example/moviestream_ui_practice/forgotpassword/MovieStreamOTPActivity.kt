package com.example.moviestream_ui_practice.forgotpassword

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.content.res.ResourcesCompat
import com.example.moviestream_ui_practice.R
import com.example.moviestream_ui_practice.utils.Constant
import com.example.moviestream_ui_practice.utils.colorMyText
import kotlinx.android.synthetic.main.activity_movie_stream_otpactivity.btnVerifyOtp
import kotlinx.android.synthetic.main.activity_movie_stream_otpactivity.etOtpFifth
import kotlinx.android.synthetic.main.activity_movie_stream_otpactivity.etOtpFirst
import kotlinx.android.synthetic.main.activity_movie_stream_otpactivity.etOtpForth
import kotlinx.android.synthetic.main.activity_movie_stream_otpactivity.etOtpSecond
import kotlinx.android.synthetic.main.activity_movie_stream_otpactivity.etOtpSixth
import kotlinx.android.synthetic.main.activity_movie_stream_otpactivity.etOtpThird
import kotlinx.android.synthetic.main.activity_movie_stream_otpactivity.tvResendOtp
import kotlinx.android.synthetic.main.activity_movie_stream_otpactivity.tvSubTitleOtp

class MovieStreamOTPActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_stream_otpactivity)
        supportActionBar?.hide()
        tvSubTitleOtp.text = colorMyText(getString(R.string.digit_code_), Constant.THIRTY, Constant.FIFTYFOUR, resources.getColor(R.color.primary_color)){ onClickOtpEmail() }
        tvResendOtp.text = colorMyText(getString(R.string.didn_t_receive_otp), Constant.TWENTYFOUR, Constant.THIRTYTWO, resources.getColor(R.color.primary_color)){ onClickResendOtp()}

        setFocus(null, etOtpFirst, etOtpSecond)
        setFocus(etOtpFirst, etOtpSecond, etOtpThird)
        setFocus(etOtpSecond, etOtpThird, etOtpForth)
        setFocus(etOtpThird, etOtpForth, etOtpFifth)
        setFocus(etOtpForth, etOtpFifth, etOtpSixth)
        setFocus(etOtpFifth, etOtpSixth, null)

        btnVerifyOtp.setOnClickListener {
            val intent = Intent(this@MovieStreamOTPActivity, MovieStreamResetPasswordActivity::class.java)
            startActivity(intent)
        }
    }

    private fun onClickResendOtp() {
        TODO("Not yet implemented")
    }

    private fun onClickOtpEmail() {
        TODO("Not yet implemented")
    }

    private fun setFocus(beforeEt: EditText?, currentEt: EditText, afterEt: EditText?) {
        currentEt.isCursorVisible = false
        currentEt.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, count: Int, p3: Int) {
                currentEt.background = ResourcesCompat.getDrawable(resources,R.drawable.otp_onfocused_background,null)
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, count: Int) {
                if (count == Constant.ONE) {
                    val next = afterEt?.let {
                        afterEt.requestFocus()
                    } ?: run {
                        currentEt.clearFocus()
                        val imm =
                            getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                        imm?.hideSoftInputFromWindow(currentEt.windowToken, Constant.ZERO)
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
        currentEt.setOnKeyListener(View.OnKeyListener { view, event, keyEvent ->
            if (event == KeyEvent.KEYCODE_DEL && currentEt.text.isEmpty()) {
                val previous = beforeEt?.let {
                    beforeEt.requestFocus()
                } ?: run {
                }
            } else if (event != KeyEvent.KEYCODE_DEL && currentEt.text.isNotEmpty()) {
                afterEt?.text = SpannableStringBuilder(keyEvent.displayLabel.toString())
            }
            return@OnKeyListener false
        })
    }
}

