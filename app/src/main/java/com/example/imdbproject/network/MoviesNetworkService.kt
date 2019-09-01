package com.example.imdbproject.network

import com.example.imdbproject.model.DataRetrofitModel
import retrofit2.Call
import retrofit2.http.GET

interface MoviesNetworkService {
    @GET("inTheaters?token=a788a35e-535e-49eb-8a18-be07c4dcebe8&format=json&language=en-us")
    fun getMovies(): Call<DataRetrofitModel>
}