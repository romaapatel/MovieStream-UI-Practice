package com.example.moviestream_ui_practice.utils.webServices

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiInterface {
    @POST("/api/register")
    fun registerApi(@Body registerRequest : RequestModel) : Call<Any>

    @POST("/api/login")
    fun loginApi(@Body loginRequest : RequestModel) : Call<Any>

    @Multipart
    @POST("upload")
    fun uploadImage (@Part image: MultipartBody.Part) : Call<Any>

    companion object {
        fun getRetrofitObject(): ApiInterface {
            return Retrofit.Builder()
                .baseUrl("https://reqres.in/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiInterface::class.java)
        }

        fun uploadImage() : ApiInterface {
            return  Retrofit.Builder().baseUrl("https://api.imgur.com/3/").addConverterFactory(GsonConverterFactory.create()).build().create(ApiInterface::class.java)
        }
    }
}