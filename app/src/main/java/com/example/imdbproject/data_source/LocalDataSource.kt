package com.example.imdbproject.data_source

import com.example.imdbproject.model.MoviesRetrofitModel

class LocalDataSource {
    companion object {
        private var ourInstance: LocalDataSource? = null

        fun getInstance(): LocalDataSource {
            if (ourInstance == null) {
                ourInstance = LocalDataSource()
            }

            return ourInstance!!
        }
    }

    private var currentlyInTheaterMoviesList: List<MoviesRetrofitModel>? = null

    fun saveMoviesList(moviesRetrofitModelList: List<MoviesRetrofitModel>?) {
        currentlyInTheaterMoviesList = moviesRetrofitModelList
    }

    fun getMoviesList(): List<MoviesRetrofitModel>? {
        return currentlyInTheaterMoviesList
    }
}