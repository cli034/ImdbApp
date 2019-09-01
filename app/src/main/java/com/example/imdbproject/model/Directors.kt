package com.example.imdbproject.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Directors {
    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("id")
    @Expose
    var id: String? = null
}