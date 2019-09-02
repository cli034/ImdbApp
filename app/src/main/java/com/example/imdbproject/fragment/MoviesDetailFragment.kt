package com.example.imdbproject.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar

import com.example.imdbproject.R

/**
 * A simple [Fragment] subclass.
 */
class MoviesDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //activity?.findViewById<Toolbar>(R.id.mainToolbar)?.title = "temp name"
    }

    override fun onResume() {
        super.onResume()
        activity?.findViewById<Toolbar>(R.id.mainToolbar)?.title = "temp name"
    }


}
