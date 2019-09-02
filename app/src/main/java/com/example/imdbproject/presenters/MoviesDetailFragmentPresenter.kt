package com.example.imdbproject.presenters

import android.os.Bundle
import com.example.imdbproject.data_source.LocalDataSource
import com.example.imdbproject.model.Directors

class MoviesDetailFragmentPresenter(private val moviesDetailFragmentPresenterInterface: MoviesDetailFragmentPresenterInterface) {
    val localDataSource = LocalDataSource.getInstance()

    interface MoviesDetailFragmentPresenterInterface {
        fun notifyViewOfMoviesPositionInList(position: Int)
        fun notifyViewToChangeToolbarTitle(title: String)
        fun notifyViewToHideRefreshButton()
        fun notifyViewToLoadImagePoster(urlPoster: String)
        fun notifyViewToSetRuntimeTextView(runtime: String)
        fun notifyViewToSetRatedTextView(rated: String)
        fun notifyViewToSetGenresTextView(genres: String)
        fun notifyViewToSetDirectorNamesTextView(directorNames: String)
        fun notifyViewToSetLanguageTextView(languages: String)
        fun notifyViewToSetPlotTextView(plot: String)
    }

    fun notifyPresenterOfOnViewCreated(arguments: Bundle?) {
        val moviesPositionInList = arguments?.getInt("position")
        if (moviesPositionInList != null) {
            moviesDetailFragmentPresenterInterface.notifyViewOfMoviesPositionInList(moviesPositionInList)
        }
    }

    fun notifyPresenterOfOnResume(position: Int?) {
        moviesDetailFragmentPresenterInterface.notifyViewToHideRefreshButton()
        val testList = localDataSource.getMoviesList()
        if (position != null && testList != null) {
            val title = testList[position].title
            val urlPoster = testList[position].urlPoster
            val runtime = testList[position].runtime
            val rated = testList[position].rated
            val genres = testList[position].genres
            val directors = testList[position].directors
            val languages = testList[position].languages
            val plot = testList[position].plot
            processToolbarTitle(title)
            processUrlPoster(urlPoster)
            processRuntime(runtime)
            processRated(rated)
            processGenres(genres)
            processDirectors(directors)
            processLanguages(languages)
            processPlot(plot)
        }
    }

    private fun processPlot(plot: String?) {
        if (plot != null) {
            moviesDetailFragmentPresenterInterface.notifyViewToSetPlotTextView(plot)
        }
    }

    private fun processLanguages(languages: List<String>?) {
        if (languages != null) {
            var languageText = ""
            for (language in languages) {
                if (languageText == "") {
                    languageText = language
                } else {
                    languageText = "$languageText, $language"
                }
            }
            moviesDetailFragmentPresenterInterface.notifyViewToSetLanguageTextView(languageText)
        }
    }

    private fun processDirectors(directors: List<Directors>?) {
        if (directors != null) {
            var directorNames = ""
            for (director in directors) {
                if (director.name != null) {
                    if (directorNames == "") {
                        directorNames = director.name!!
                    } else {
                        directorNames = directorNames + ", " + director.name
                    }
                }
            }
            moviesDetailFragmentPresenterInterface.notifyViewToSetDirectorNamesTextView(directorNames)
        }
    }

    private fun processGenres(genres: List<String>?) {
        if (genres != null) {
            var genreText = ""
            for (genre in genres) {
                if (genreText == "") {
                    genreText = genre
                } else {
                    genreText = "$genreText, $genre"
                }
            }
            moviesDetailFragmentPresenterInterface.notifyViewToSetGenresTextView(genreText)
        }
    }

    private fun processRated(rated: String?) {
        if (rated != null) {
            moviesDetailFragmentPresenterInterface.notifyViewToSetRatedTextView(rated)
        }
    }

    private fun processRuntime(runtime: String?) {
        if (runtime != null) {
            moviesDetailFragmentPresenterInterface.notifyViewToSetRuntimeTextView(runtime)
        }
    }

    private fun processUrlPoster(urlPoster: String?) {
        if (urlPoster != null) {
            moviesDetailFragmentPresenterInterface.notifyViewToLoadImagePoster(urlPoster)
        }
    }

    fun processToolbarTitle(title: String?) {
        if (title != null) {
            moviesDetailFragmentPresenterInterface.notifyViewToChangeToolbarTitle(title)
        }
    }
}