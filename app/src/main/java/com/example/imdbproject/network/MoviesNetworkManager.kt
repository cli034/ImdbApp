package com.example.imdbproject.network

import com.example.imdbproject.model.DataRetrofitModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MoviesNetworkManager {
    private val IMDB_BASE_URL = "https://www.myapifilms.com/imdb/"

    val moviesRetrofit: Retrofit

    init {
        moviesRetrofit = Retrofit.Builder()
            .baseUrl(IMDB_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    companion object {
        private val lock = Object()
        @Volatile private var instance1: MoviesNetworkManager? = null
        fun getInstance(): MoviesNetworkManager {
            var doubleCheckInstance = instance1
            if (doubleCheckInstance == null) {
                synchronized(lock) {
                    doubleCheckInstance = instance1
                    if (doubleCheckInstance == instance1) {
                        instance1 = MoviesNetworkManager()
                    }
                }
            }
            return instance1!!
        }
    }

    interface MoviesNetworkInterface {
        fun onSuccess(dataRetrofitModel: DataRetrofitModel)
        fun onError()
    }

    fun getMoviesList(moviesNetworkInterface: MoviesNetworkInterface) {
        val moviesNetworkService = moviesRetrofit.create(MoviesNetworkService::class.java)
        val call = moviesNetworkService.getMovies()

        call.enqueue(object: Callback<DataRetrofitModel> {
            override fun onFailure(call: Call<DataRetrofitModel>, t: Throwable) {
                t.printStackTrace()
                moviesNetworkInterface.onError()
            }

            override fun onResponse(
                call: Call<DataRetrofitModel>,
                response: Response<DataRetrofitModel>
            ) {
                var responseBody = response.body()
                if (responseBody == null) {
                    moviesNetworkInterface.onError()
                } else {
                    moviesNetworkInterface.onSuccess(responseBody)
                }
            }

        })
    }
}