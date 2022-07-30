package com.axichise.movieapp.ui.actors

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
        val actorImage: ImageView = view.findViewById(R.id.actorIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_actors, parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val actors = actorsList[position]
        holder.actorName.text = actors.name

        Glide.with(holder.actorImage.context).load(IMAGE_URL + actors.profile_path)
            .into(holder.actorImage)

        selectActor(holder, actors)

        holder.parentView.setOnClickListener {
            actors.isSelected = !actors.isSelected
            selectActor(holder, actors)

        }
    }
    private fun selectActor(holder: ViewHolder, actor: Actors) {
        holder.parentView.setBackgroundColor(
            when (actor.isSelected) {
                true -> ContextCompat.getColor(holder.parentView.context,
                    android.R.color.holo_orange_dark
                )
                else -> ContextCompat.getColor(holder.parentView.context, R.color.white)
            }
        )

        holder.actorName.setTextColor(
            when (actor.isSelected) {
                true -> ContextCompat.getColor(holder.parentView.context, R.color.white)
                else -> ContextCompat.getColor(holder.parentView.context, R.color.black)
            }
        )
    }

    override fun getItemCount() = actorsList.size
}
