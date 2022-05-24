package com.example.moviestream_ui_practice.utils.webServices

import android.app.Dialog
import android.content.Context
import android.text.InputType
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.moviestream_ui_practice.R
import com.example.moviestream_ui_practice.utils.Constant
import com.example.moviestream_ui_practice.utils.emailValidator
import com.example.moviestream_ui_practice.utils.loadImage
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_movie_stream_login.btnLogin
import kotlinx.android.synthetic.main.activity_movie_stream_login.progress_circular
import kotlinx.android.synthetic.main.custom_dialog.imageGIF
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class RetrofitBaseActivity : AppCompatActivity() {

    private lateinit var dialog: Dialog
    var loadingBtn = true
    var currentProgress = 0

    fun putToSharedPref(key: String, value: Any) {
        val editor =  getSharedPreferences(Constant.ERRORKEY, Context.MODE_PRIVATE).edit()
        when (value) {
            is String -> {
                editor.putString(key,value)
            }
            is Int -> {
                editor.putInt(key,value)
            }
            is Float -> {
                editor.putFloat(key,value)
            }
            is Long -> {
                editor.putLong(key,value)
            }
            is Boolean -> {
                editor.putBoolean(key,value)
            }
        }
        editor.apply()
    }

    fun sendPostRequestRetrofit(user: RequestModel, processes: Processes,view: View, responseHandler: RetrofitResponseHandler) {
        val call: Call<Any> = when(processes) {
            Processes.Login -> ApiInterface.getRetrofitObject().loginApi(user)
            Processes. Register-> ApiInterface.getRetrofitObject().registerApi(user)
        }

        call.enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                if (response.isSuccessful) {
                    responseHandler.onAPISuccess(response.body().toString())
                    progressBarButton(loadingBtn)
                } else {
                    val errorResponse = response.errorBody()?.charStream()?.readText()
                    errorResponse?.let { JSONObject(it) }
                    setSnackBar(getString(R.string.credential_do_not_match), view)
                }
            }

            override fun onFailure(call: Call<Any>, throwable: Throwable) {
                throwable.message?.let { responseHandler.onRetrofitFailure(it) }
                endLoading()
            }
        })
    }

     fun startLoading() {
        dialog = Dialog(this)
        dialog.setContentView(R.layout.custom_dialog)
        loadImage(Constant.GIFIMAGE, dialog.imageGIF)
        dialog.show()
        dialog.window?.setLayout(Constant.FIVE_H.toInt(), Constant.FIVE_H.toInt())
    }

     fun endLoading() {
        dialog.dismiss()
    }

    fun isValidFields(email: EditText, password: EditText, view: View): Boolean {
        val isEmailValid = emailValidator(email.text.toString().trim())
        val isPasswordValid = password.text.toString().trim().matches(Constant.PASSWORDPATTERN)

        if (email.text.toString().isEmpty() && password.text.toString().isEmpty()) {
            setSnackBar(getString(R.string.empty_password_email), view)
        } else if(email.text.toString().isEmpty()){
            setSnackBar(getString(R.string.empty_email), view)
        } else if(password.text.toString().isEmpty()) {
            setSnackBar(getString(R.string.empty_password_error), view)
        } else if (!isEmailValid) {
            setSnackBar(getString(R.string.wrong_email), view)
        } else if (password.text.toString().length < Constant.EIGHT) {
            setSnackBar(getString(R.string.password_length), view)
        } else if(!isPasswordValid) {
            setSnackBar(getString(R.string.invalid_password), view)
        }
        return isEmailValid && isPasswordValid
    }

    private fun setSnackBar(message: String, view: View) {
        val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG).setTextColor(resources.getColor(R.color.black)).setBackgroundTint(resources.getColor(R.color.red_orange))
        val params = snackbar.view.layoutParams as FrameLayout.LayoutParams
        params.gravity = Gravity.TOP
        snackbar.view.layoutParams = params
        snackbar.show()
    }

    fun setupPasswordToggle(isPasswordHidden: Boolean, field: EditText, toggleButton: ImageButton) {
        if (isPasswordHidden) {
            toggleButton.setImageResource(R.drawable.ic_hidden)
            field.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        } else {
            toggleButton.setImageResource(R.drawable.ic_eye)
            field.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        }
    }

    fun progressBarButton(isLoading: Boolean) {
        progress_circular.progress = currentProgress
        if (isLoading) {
            btnLogin.visibility = View.INVISIBLE
            progress_circular.visibility = View.VISIBLE
        } else {
            btnLogin.visibility = View.VISIBLE
            progress_circular.visibility = View.INVISIBLE
        }
    }
}
