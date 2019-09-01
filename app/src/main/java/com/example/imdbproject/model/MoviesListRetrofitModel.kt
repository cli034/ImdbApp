package com.example.imdbproject.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MoviesListRetrofitModel {
    @SerializedName("movies")
    @Expose
    var movies: List<MoviesRetrofitModel>? = null
}