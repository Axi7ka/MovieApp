package com.axichise.movieapp.ui.actors


class ActorsMapper {
    fun map(actorsResponse: ActorsResponse): Actors {
        return Actors(
            id = actorsResponse.id,
            name = actorsResponse.name,
            isSelected = false
        )
    }
}