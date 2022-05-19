package com.example.moviestream_ui_practice.utils.webservices

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseModel {
    @SerializedName ("token")
    @Expose
    var token : String? = null
}