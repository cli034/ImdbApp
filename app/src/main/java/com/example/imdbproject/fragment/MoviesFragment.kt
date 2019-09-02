package com.example.imdbproject.fragment


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.findViewById<Toolbar>(R.id.mainToolbar)?.title = "IMDB"
        moviesFragmentPresenter = MoviesFragmentPresenter(moviesFragmentPresenterInterface)
        initListeners()
        setupRecyclerView()
    }

    private fun initListeners() {
        val mainRefreshTextView = activity?.findViewById<TextView>(R.id.mainRefreshTextView)
        mainRefreshTextView?.setOnClickListener {
            moviesFragmentPresenter.notifyPresenterOfMainRefreshTextViewOnClick()
        }
    }

    private fun setupRecyclerView() {
        moviesRecyclerAdapter = MoviesFragmentRecyclerAdapter(moviesFragmentAdapterInterface)
        val layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        fragmentMoviesRecyclerView.adapter = moviesRecyclerAdapter
        fragmentMoviesRecyclerView.layoutManager = layoutManager
    }

    override fun onResume() {
        super.onResume()
        moviesFragmentPresenter.notifyPresenterOfOnResume()
    }

    val moviesFragmentAdapterInterface = object: MoviesFragmentRecyclerAdapter.MoviesFragmentAdapterInterface {
        override fun notifyPresenterOfMoviesViewDetailOnClick(position: Int) {
            Log.d("click_listener", position.toString())
            findNavController().navigate(R.id.action_moviesFragment_to_moviesDetailFragment)
        }

    }

    val moviesFragmentPresenterInterface = object: MoviesFragmentPresenter.MoviesFragmentPresenterInterface {
        override fun notifyViewToHideRecyclerView() {
            fragmentMoviesRecyclerView?.visibility = View.INVISIBLE
        }

        override fun notifyViewToShowRecyclerView() {
            fragmentMoviesRecyclerView?.visibility = View.VISIBLE
        }

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
