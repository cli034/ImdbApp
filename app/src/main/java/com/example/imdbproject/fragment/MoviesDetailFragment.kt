package com.example.imdbproject.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide

import com.example.imdbproject.R
import com.example.imdbproject.presenters.MoviesDetailFragmentPresenter
import kotlinx.android.synthetic.main.fragment_movies_detail.*

/**
 * A simple [Fragment] subclass.
 */
class MoviesDetailFragment : Fragment() {
    lateinit var moviesDetailFragmentPresenter: MoviesDetailFragmentPresenter
    var moviesPositionInList: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moviesDetailFragmentPresenter = MoviesDetailFragmentPresenter(moviesDetailFragmentPresenterInterface)
        view.setBackgroundColor(resources.getColor(R.color.colorWhite))
        moviesDetailFragmentPresenter.notifyPresenterOfOnViewCreated(arguments)
    }

    override fun onResume() {
        super.onResume()
        moviesDetailFragmentPresenter.notifyPresenterOfOnResume(moviesPositionInList)
    }

    val moviesDetailFragmentPresenterInterface = object: MoviesDetailFragmentPresenter.MoviesDetailFragmentPresenterInterface {
        override fun notifyViewToLoadImagePoster(urlPoster: String) {
            if (context != null) {
                Glide.with(context!!)
                    .load(urlPoster)
                    .into(moviesDetailImageView)
            }
        }

        override fun notifyViewToSetRuntimeTextView(runtime: String) {
            moviesDetailRuntimeTextView?.text = runtime
        }

        override fun notifyViewToSetRatedTextView(rated: String) {
            moviesDetailRatedTextView?.text = rated
        }

        override fun notifyViewToSetGenresTextView(genres: String) {
            moviesDetailGenreDetailTextView?.text = genres
        }

        override fun notifyViewToSetDirectorNamesTextView(directorNames: String) {
            moviesDetailDirectorNameTextView?.text = directorNames
        }

        override fun notifyViewToSetLanguageTextView(languages: String) {
            moviesDetailLanguageDetailTextView?.text = languages
        }

        override fun notifyViewToSetPlotTextView(plot: String) {
            moviesDetailPlotTextView?.text = plot
        }

        override fun notifyViewOfMoviesPositionInList(position: Int) {
            moviesPositionInList = position
        }

        override fun notifyViewToHideRefreshButton() {
            activity?.findViewById<TextView>(R.id.mainRefreshTextView)?.visibility = View.GONE
        }

        override fun notifyViewToChangeToolbarTitle(title: String) {
            activity?.findViewById<Toolbar>(R.id.mainToolbar)?.title = title
        }

    }


}
