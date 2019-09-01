package com.example.imdbproject.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imdbproject.R
import com.example.imdbproject.model.MoviesRetrofitModel
import kotlinx.android.synthetic.main.item_movies.view.*

class MoviesFragmentRecyclerAdapter():
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var moviesRetrofitModelList: List<MoviesRetrofitModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movies, parent, false)
        return MoviesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return moviesRetrofitModelList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MoviesViewHolder) {
            Glide.with(holder.itemView.context)
                .load(moviesRetrofitModelList[position].urlPoster)
                .into(holder.moviesImageView)
            holder.moviesTitleTextView.text = moviesRetrofitModelList[position].title
            holder.moviesRatingTextView.text = moviesRetrofitModelList[position].rated
            holder.moviesRuntimeTextView.text = moviesRetrofitModelList[position].runtime
            holder.moviesGenreTextView.text = moviesRetrofitModelList[position].genres?.firstOrNull()
        }
    }

    inner class MoviesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val moviesImageView: ImageView = itemView.itemMoviesImageView
        val moviesTitleTextView: TextView = itemView.itemMoviesTitleTextView
        val moviesRatingTextView: TextView = itemView.itemMoviesRatingTextView
        val moviesRuntimeTextView: TextView = itemView.itemMoviesRuntimeTextView
        val moviesGenreTextView: TextView = itemView.itemMoviesGenreTextView
        val moviesViewDetailsTextView: TextView = itemView.itemMoviesViewDetailsTextView

        init {
            moviesViewDetailsTextView.setOnClickListener {

            }
        }
    }
}