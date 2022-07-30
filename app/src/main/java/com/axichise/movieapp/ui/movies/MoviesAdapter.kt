package com.axichise.movieapp.ui.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.axichise.movieapp.R
import com.axichise.movieapp.ui.utils.Constants
import com.bumptech.glide.Glide

class MoviesAdapter (private val moviesList: List<Movies>) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val movieName: TextView = view.findViewById(R.id.tvName)
        val parentView: ConstraintLayout = view.findViewById(R.id.parent)
        val movieImage: ImageView = view.findViewById(R.id.movieIcon)
        val movieOverview: TextView = view.findViewById(R.id.tvDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movies = moviesList[position]
        holder.movieName.text = movies.title
        holder.movieOverview.text = movies.overview

        Glide.with(holder.movieImage.context).load(Constants.IMAGE_URL + movies.poster_path)
            .into(holder.movieImage)


    }

    override fun getItemCount() = moviesList.size
}