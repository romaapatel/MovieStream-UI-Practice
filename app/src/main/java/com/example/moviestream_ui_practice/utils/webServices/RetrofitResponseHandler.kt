package com.example.moviestream_ui_practice.utils.webServices

interface RetrofitResponseHandler {

    fun onAPISuccess(response: String)
    fun onAPIFailure(message: String)
    fun onRetrofitFailure(message: String)

}