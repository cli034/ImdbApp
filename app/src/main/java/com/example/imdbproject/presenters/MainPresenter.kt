package com.example.imdbproject.presenters

class MainPresenter (private val mainPresenterInterface: MainPresenterInterface) {
    interface MainPresenterInterface {
        fun onResume()
    }

    fun notifyPresenterOfOnResume() {
        mainPresenterInterface.onResume()
    }

}