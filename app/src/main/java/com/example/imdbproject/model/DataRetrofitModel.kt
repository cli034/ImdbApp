package com.example.imdbproject.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DataRetrofitModel {
    @SerializedName("data")
    @Expose
    var data: InTheatersRetrofitModel? = null
}