package com.axichise.movieapp.ui.genres

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.axichise.movieapp.R

class GenresAdapter(private val genreList: List<Genre>) :
    RecyclerView.Adapter<GenresAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val genreName: TextView = view.findViewById(R.id.tvName)
        val parentView: ConstraintLayout = view.findViewById(R.id.parent)
        val starIcon: ImageView = view.findViewById(R.id.icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_genre, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val genre = genreList[position]
        holder.genreName.text = genre.name

        selectGenre(holder,genre)

        holder.parentView.setOnClickListener {
            genre.isSelected = !genre.isSelected
            selectGenre(holder, genre)

        }
    }

    private fun selectGenre(holder: ViewHolder,genre: Genre){
        holder.parentView.setBackgroundColor(
            when (genre.isSelected) {
                true -> ContextCompat.getColor(
                    holder.parentView.context, android.R.color.holo_orange_dark)
                else -> ContextCompat.getColor(
                    holder.parentView.context, android.R.color.white)
            }
        )
        holder.starIcon.visibility = when(genre.isSelected){
            true -> View.VISIBLE
            else -> View.INVISIBLE
        }
    }

    override fun getItemCount() = genreList.size
}
