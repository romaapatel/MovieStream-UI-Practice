package com.example.moviestream_ui_practice.utils.webservices

import android.app.Dialog
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.moviestream_ui_practice.R
import com.example.moviestream_ui_practice.utils.Constant
import com.example.moviestream_ui_practice.utils.emailValidator
import com.example.moviestream_ui_practice.utils.loadImage
import kotlinx.android.synthetic.main.custom_dialog.imageGIF
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

open class BaseActivity : AppCompatActivity() {

     private lateinit var dialog: Dialog

     fun sendPostRequest(data: JSONObject, apiUrl: String, passingRequestMethod: String, callback: ApiCallBack): Boolean {
         var result = false
         val requestModel = RequestModel()
         val responseModel = ResponseModel()
         startLoading()
         thread {
             val url = URL(apiUrl)
             (url.openConnection() as HttpURLConnection).apply {
                 requestMethod = passingRequestMethod
                 setRequestProperty("Content-Type", "application/json")
                 val outputStreamWriter = OutputStreamWriter(outputStream)
                 outputStreamWriter.write(data.toString())
                 outputStreamWriter.flush()
                 result =  if (responseCode == Constant.SUCCESS_RESPONSE_CODE) {
                     runCatching {
                         callback.onSuccess()
                         BufferedReader(InputStreamReader(inputStream)).use { reader ->
                             val response = StringBuffer()
                             val inputLine = reader.readLine()
                             response.append(inputLine)
                             val jsonObject = JSONObject(response.toString())
                             Log.d(responseModel.token, jsonObject.getString("token"))
                             Log.d(requestModel.email, jsonObject.getString("email"))
                         }
                         endLoading()
                     }
                     true
                 } else {
                     if (responseCode == Constant.FAILURE_RESPONSE_CODE) {
                        runCatching {
                             callback.onFailure()
                             endLoading()
                         }
                     }
                     false
                 }
             }
         }
         return result
    }

     private fun startLoading() {
        dialog = Dialog(this)
        dialog.setContentView(R.layout.custom_dialog)
        loadImage(Constant.GIFIMAGE, dialog.imageGIF)
        dialog.show()
        dialog.window?.setLayout(Constant.FIVEHUNDRED.toInt(), Constant.FIVEHUNDRED.toInt())
    }

     private fun endLoading() {
        dialog.dismiss()
     }

     fun isValidFields(email: EditText, password: EditText): Boolean {
        val isEmailValid = emailValidator(email.text.toString().trim()) && email.text.toString().isNotEmpty()
        val isPasswordValid = password.text.toString().length > Constant.EIGHT && password.text.toString().isNotEmpty()
        Toast.makeText(this, isEmailValid.toString(), Toast.LENGTH_SHORT).show()

        if (isEmailValid) {
            email.error = null
        } else {
            email.error = getString(R.string.wrong_email)
        }

        if (isPasswordValid) {
            password.error = null
        } else {
            password.error = getString(R.string.invalid_password)
        }
        return isEmailValid && isPasswordValid
    }
}