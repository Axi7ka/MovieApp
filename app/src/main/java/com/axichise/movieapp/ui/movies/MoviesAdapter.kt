package com.axichise.movieapp.ui.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.axichise.movieapp.R
import com.axichise.movieapp.ui.movieDetails.MovieDetailViewModel
import com.axichise.movieapp.ui.utils.Constants
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MoviesAdapter (private val moviesList: List<Movies>,
                     private val detailsCallback: (() -> Unit)?,
                     private val viewModel:MovieDetailViewModel
) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)

        return ViewHolder(view)
    }
    private val moviesRepository: MoviesRepository = MoviesRepository.instance

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var favorite: Boolean = false
        var watched:Boolean = false
        val parentView: LinearLayout = view.findViewById(R.id.parent)
        val movieName: TextView = view.findViewById(R.id.tvName)
        val movieImage: ImageView = view.findViewById(R.id.movieIcon)
        val movieOverview: TextView = view.findViewById(R.id.tvDescription)
        val itemButtonFavorites: ImageView = view.findViewById(R.id.ivLikedBorder)
        val itemButtonWatched: ImageView = view.findViewById(R.id.ivSeenBorder)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movies = moviesList[position]
        holder.movieName.text = movies.title
        holder.movieOverview.text = movies.overview
        holder.favorite = movies.isFavorite
        holder.watched = movies.isWatched

        Glide.with(holder.movieImage.context).load(Constants.IMAGE_URL + movies.poster_path)
            .into(holder.movieImage)
        updateFavoriteButton(holder)
        updateWatchedButton(holder)

        holder.itemButtonFavorites.setOnClickListener{
            holder.favorite = !holder.favorite
            movies.isFavorite = holder.favorite
            updateFavoriteButton(holder)
            updateDatabase(moviesList[position])
        }

        holder.itemButtonWatched.setOnClickListener{
            holder.watched = !holder.watched
            movies.isWatched = holder.watched
            updateWatchedButton(holder)
            updateDatabase(moviesList[position])
        }

        holder.parentView.setOnClickListener{
            viewModel.currentMovieId.postValue(movies.id)
            detailsCallback?.invoke()
        }


    }

    private fun updateFavoriteButton(holder: ViewHolder) {
        holder.itemButtonFavorites.setImageResource(when(holder.favorite) {
            true -> R.drawable.ic_liked
            else -> R.drawable.ic_liked_border
        })
    }

    private fun updateWatchedButton(holder: ViewHolder) {
        holder.itemButtonWatched.setImageResource(when(holder.watched) {
            true -> R.drawable.ic_seen
            else -> R.drawable.ic_seen_border
        })
    }
    private fun filterWithFlags() = moviesList.filter { it.isFavorite || it.isWatched }

    private fun updateDatabase(item: Movies) {
        GlobalScope.launch(Dispatchers.IO) {
            val saved = ArrayList<Movies>(moviesRepository.getAllLocalMovies())
            val filtered = ArrayList<Movies>(filterWithFlags())

            saved.remove(item)

            moviesRepository.replaceAllLocal(saved.union(filtered).toList())
        }
    }

    override fun getItemCount() = moviesList.size
}