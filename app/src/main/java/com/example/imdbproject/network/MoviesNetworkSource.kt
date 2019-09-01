package com.example.imdbproject.network

import com.example.imdbproject.model.DataRetrofitModel
import com.example.imdbproject.model.InTheatersRetrofitModel

class MoviesNetworkSource {
    val moivesNetworkManager: MoviesNetworkManager

    companion object {
        private var ourInstance: MoviesNetworkSource? = null

        fun getInstance(): MoviesNetworkSource {
            if (ourInstance == null) {
                ourInstance = MoviesNetworkSource()
            }

            return ourInstance!!
        }
    }

    private constructor() {
        moivesNetworkManager = MoviesNetworkManager()
    }

    interface MoviesNetworkSourceInterface {
        fun onSuccess(inTheatersRetrofitModel: InTheatersRetrofitModel)
        fun onError()
    }

    fun getMoviesList(moviesNetworkSourceInterface: MoviesNetworkSourceInterface) {
        moivesNetworkManager.getMoviesList(object: MoviesNetworkManager.MoviesNetworkInterface {
            override fun onSuccess(dataRetrofitModel: DataRetrofitModel) {
                var inTheatersRetrofitModel = dataRetrofitModel.data
                if (inTheatersRetrofitModel == null) {
                    moviesNetworkSourceInterface.onError()
                } else {
                    moviesNetworkSourceInterface.onSuccess(inTheatersRetrofitModel)
                }
            }

            override fun onError() {
                moviesNetworkSourceInterface.onError()
            }

        })
    }
}