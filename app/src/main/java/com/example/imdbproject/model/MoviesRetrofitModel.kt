package com.example.imdbproject.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MoviesRetrofitModel {
    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("rated")
    @Expose
    var rated: String? = null

    @SerializedName("runtime")
    @Expose
    var runtime: String? = null

    @SerializedName("genres")
    @Expose
    var genres: List<String>? = null

    @SerializedName("directors")
    @Expose
    var directors: List<Directors>? = null

    @SerializedName("languages")
    @Expose
    var languages: List<String>? = null

    @SerializedName("plot")
    @Expose
    var plot: String? = null

    @SerializedName("urlPoster")
    @Expose
    var urlPoster: String? = null

}