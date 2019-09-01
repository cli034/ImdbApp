package com.example.imdbproject.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class InTheatersRetrofitModel {
    @SerializedName("inTheaters")
    @Expose
    var inTheaters: List<MoviesListRetrofitModel>? = null
}