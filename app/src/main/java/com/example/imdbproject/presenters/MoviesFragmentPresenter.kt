package com.example.imdbproject.presenters

import com.example.imdbproject.AppConstant
import com.example.imdbproject.data_source.LocalDataSource
import com.example.imdbproject.model.InTheatersRetrofitModel
import com.example.imdbproject.model.MoviesRetrofitModel
import com.example.imdbproject.network.MoviesNetworkSource

class MoviesFragmentPresenter(private val moviesFragmentPresenterInterface: MoviesFragmentPresenterInterface) {
    val moviesNetworkSource = MoviesNetworkSource.getInstance()
    val localDataSource = LocalDataSource.getInstance()

    interface MoviesFragmentPresenterInterface {
        fun notifyViewToChangeToolbarTitle()
        fun notifyViewToShowRefreshButton()
        fun notifyViewOfUpdateAdapterDataSet(moviesRetrofitModelList: List<MoviesRetrofitModel>)
        fun notifyViewToHideProgressBar()
        fun notifyViewToShowProgressBar()
        fun notifyViewToHideRecyclerView()
        fun notifyViewToShowRecyclerView()
    }

    fun notifyPresenterOfOnResume() {
        moviesFragmentPresenterInterface.notifyViewToChangeToolbarTitle()
        moviesFragmentPresenterInterface.notifyViewToShowRefreshButton()
        moviesFragmentPresenterInterface.notifyViewToShowProgressBar()
        moviesFragmentPresenterInterface.notifyViewToHideRecyclerView()
        val moviesRetrofitModelList = localDataSource.getMoviesList()
        if (moviesRetrofitModelList != null) {
            moviesFragmentPresenterInterface.notifyViewOfUpdateAdapterDataSet(moviesRetrofitModelList)
            moviesFragmentPresenterInterface.notifyViewToShowRecyclerView()
            moviesFragmentPresenterInterface.notifyViewToHideProgressBar()
        } else {
            processDownloadCurrentlyInTheaterMoviesList()
        }
    }

    fun processDownloadCurrentlyInTheaterMoviesList() {
        moviesNetworkSource.getMoviesList(object: MoviesNetworkSource.MoviesNetworkSourceInterface {
            override fun onSuccess(inTheatersRetrofitModel: InTheatersRetrofitModel) {
                var moviesRetrofitModelList = inTheatersRetrofitModel.inTheaters?.getOrNull(AppConstant.IN_THEATHER_MOVIES_INDEX)?.movies
                if (moviesRetrofitModelList != null) {
                    localDataSource.saveMoviesList(moviesRetrofitModelList)
                    moviesFragmentPresenterInterface.notifyViewOfUpdateAdapterDataSet(moviesRetrofitModelList)
                    moviesFragmentPresenterInterface.notifyViewToShowRecyclerView()
                    moviesFragmentPresenterInterface.notifyViewToHideProgressBar()
                }
            }

            override fun onError() {

            }

        })
    }

    fun notifyPresenterOfMainRefreshTextViewOnClick() {
        localDataSource.saveMoviesList(null)
        moviesFragmentPresenterInterface.notifyViewToShowProgressBar()
        moviesFragmentPresenterInterface.notifyViewToHideRecyclerView()
        processDownloadCurrentlyInTheaterMoviesList()
    }

}