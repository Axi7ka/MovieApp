package com.axichise.movieapp.ui.SavedMovies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.axichise.movieapp.R
import com.axichise.movieapp.ui.movies.Movies
import com.axichise.movieapp.ui.movies.MoviesRepository
import com.axichise.movieapp.ui.utils.Constants
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class WatchedMoviesAdapter(
    private val moviesList: MutableList<Movies>
) : RecyclerView.Adapter<WatchedMoviesAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var favorite: Boolean = false
        var watched: Boolean = false

        val itemIvMovie = view.findViewById<ImageView>(R.id.movieIcon)!!
        val itemIvTitle = view.findViewById<TextView>(R.id.tvName)!!
        val itemIvOverview = view.findViewById<TextView>(R.id.tvDescription)!!

        val itemBtnDelete = view.findViewById<Button>(R.id.btnDelete)!!
    }

    private val moviesRep: MoviesRepository = MoviesRepository.instance

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie_delete, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = moviesList[position]

        holder.itemIvTitle.text = movie.title
        holder.itemIvOverview.text = movie.overview
        holder.favorite = moviesList[position].isFavorite
        holder.watched = moviesList[position].isWatched

        Glide.with(holder.itemIvMovie.context).load(Constants.IMAGE_URL + movie.poster_path)
            .into(holder.itemIvMovie)

        holder.itemBtnDelete.setOnClickListener {
            updateItem(moviesList[position])
            moviesList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, moviesList.size)
        }
    }

    private fun updateItem(movie: Movies) {
        GlobalScope.launch(Dispatchers.IO) {
            val saved = ArrayList(moviesRep.getAllLocalMovies())
            val idx = saved.indexOf(movie)
            if (idx != -1) saved[idx].isFavorite = !saved[idx].isFavorite
            if (!saved[idx].isFavorite && !saved[idx].isWatched) {
                moviesRep.deleteLocal(saved[idx])
                saved.removeAt(idx)
            }
            moviesRep.replaceAllLocal(saved.toList())
        }
    }

    override fun getItemCount() = moviesList.size
}