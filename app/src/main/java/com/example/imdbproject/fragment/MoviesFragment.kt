package com.example.imdbproject.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.imdbproject.R
import com.example.imdbproject.adapters.MoviesFragmentRecyclerAdapter
import com.example.imdbproject.model.MoviesRetrofitModel
import com.example.imdbproject.presenters.MoviesFragmentPresenter
import kotlinx.android.synthetic.main.fragment_movies.*

class MoviesFragment : Fragment() {
    lateinit var moviesRecyclerAdapter: MoviesFragmentRecyclerAdapter
    lateinit var moviesFragmentPresenter: MoviesFragmentPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        moviesFragmentPresenter = MoviesFragmentPresenter(moviesFragmentPresenterInterface)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        moviesRecyclerAdapter = MoviesFragmentRecyclerAdapter()
        val layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        fragmentMoviesRecyclerView.adapter = moviesRecyclerAdapter
        fragmentMoviesRecyclerView.layoutManager = layoutManager
    }

    override fun onResume() {
        super.onResume()
        moviesFragmentPresenter.notifyPresenterOfOnResume()
    }

    val moviesFragmentPresenterInterface = object: MoviesFragmentPresenter.MoviesFragmentPresenterInterface {
        override fun notifyViewToShowProgressBar() {
            fragmentMoviesProgressBar?.visibility = View.VISIBLE
        }

        override fun notifyViewToHideProgressBar() {
            fragmentMoviesProgressBar?.visibility = View.GONE
        }

        override fun notifyViewOfUpdateAdapterDataSet(moviesRetrofitModelList: List<MoviesRetrofitModel>) {
            moviesRecyclerAdapter.moviesRetrofitModelList = moviesRetrofitModelList
            moviesRecyclerAdapter.notifyDataSetChanged()
        }

    }

}
