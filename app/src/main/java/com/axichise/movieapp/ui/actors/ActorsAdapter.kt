package com.axichise.movieapp.ui.actors

import android.media.Image
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.axichise.movieapp.R
import com.axichise.movieapp.ui.utils.Constants.IMAGE_URL
import com.bumptech.glide.Glide

class ActorsAdapter(private val actorsList: List<Actors>) :
    RecyclerView.Adapter<ActorsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val actorName: TextView = view.findViewById(R.id.tvName)
        val parentView: ConstraintLayout = view.findViewById(R.id.parent)
        val starIcon: ImageView = view.findViewById(R.id.icon)
        val actorIcon: ImageView = view.findViewById(R.id.photo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_actors, parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val actors = actorsList[position]
        holder.actorName.text = actors.name
        Glide.with(holder.starIcon.context).load(IMAGE_URL + actors.photo).into(holder.actorIcon)
        selectActor(holder, actors)

        holder.parentView.setOnClickListener {
            actors.isSelected = !actors.isSelected
            selectActor(holder, actors)

        }
    }
    private fun selectActor(holder:ViewHolder, actors: Actors){
        holder.parentView.setBackgroundColor(
            when (actors.isSelected) {
                true -> ContextCompat.getColor(
                    holder.parentView.context, android.R.color.holo_orange_dark)
                else -> ContextCompat.getColor(
                    holder.parentView.context, android.R.color.white)
            }
        )
        holder.starIcon.visibility = when(actors.isSelected){
            true -> View.VISIBLE
            else -> View.INVISIBLE
        }
    }

    override fun getItemCount() = actorsList.size
}
