package com.example.imdbproject.presenters

import com.example.imdbproject.model.InTheatersRetrofitModel
import com.example.imdbproject.model.MoviesRetrofitModel
import com.example.imdbproject.network.MoviesNetworkSource

class MoviesFragmentPresenter(private val moviesFragmentPresenterInterface: MoviesFragmentPresenterInterface) {
    val moviesNetworkSource = MoviesNetworkSource.getInstance()

    interface MoviesFragmentPresenterInterface {
        fun notifyViewOfUpdateAdapterDataSet(moviesRetrofitModelList: List<MoviesRetrofitModel>)
        fun notifyViewToHideProgressBar()
        fun notifyViewToShowProgressBar()
    }

    fun notifyPresenterOfOnResume() {
        moviesFragmentPresenterInterface.notifyViewToShowProgressBar()
        moviesNetworkSource.getMoviesList(object: MoviesNetworkSource.MoviesNetworkSourceInterface {
            override fun onSuccess(inTheatersRetrofitModel: InTheatersRetrofitModel) {
                var moviesRetrofitModelList = inTheatersRetrofitModel.inTheaters?.getOrNull(1)?.movies
                if (moviesRetrofitModelList != null) {
                    moviesFragmentPresenterInterface.notifyViewOfUpdateAdapterDataSet(moviesRetrofitModelList)
                    moviesFragmentPresenterInterface.notifyViewToHideProgressBar()
                }
            }

            override fun onError() {

            }

        })
    }

}