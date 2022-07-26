package com.axichise.movieapp.ui.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.axichise.movieapp.R
import com.axichise.movieapp.ui.utils.Constants
import com.bumptech.glide.Glide

class MoviesAdapter (private val movieList: List<Movies>) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val movieName: TextView = view.findViewById(R.id.tvName)
        val parentView: ConstraintLayout = view.findViewById(R.id.parent)
        val moviesIcon: ImageView = view.findViewById(R.id.photo)
        val moviesDescription: TextView = view.findViewById(R.id.tvOverview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movies, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movieList[position]
        holder.movieName.text = movie.title
        holder.moviesDescription.text = movie.overview
//        selectMovie(holder, movie)

        Glide.with(holder.moviesIcon.context).load(Constants.IMAGE_URL + movie.poster_path).into(holder.moviesIcon)


        holder.parentView.setOnClickListener {
            movie.isSelected = !movie.isSelected
//            selectMovie(holder, movie)

        }
    }

//    private fun selectMovie(holder: ViewHolder,movie: Movies){
//        holder.parentView.setBackgroundColor(
//            when (movie.isSelected) {
//                true -> ContextCompat.getColor(
//                    holder.parentView.context, android.R.color.holo_orange_dark)
//                else -> ContextCompat.getColor(
//                    holder.parentView.context, android.R.color.white)
//            }
//        )
//
//    }

    override fun getItemCount() = movieList.size
}
