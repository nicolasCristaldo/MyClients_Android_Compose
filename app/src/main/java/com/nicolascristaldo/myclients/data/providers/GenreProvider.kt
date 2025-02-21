package com.nicolascristaldo.myclients.data.providers

import com.nicolascristaldo.myclients.R

data class Genre(
    val nameRes: Int,
    val imageRes: Int
)

object GenreProvider {
    fun getGenreByName(name: String): Genre {
        when(name) {
            "male" -> return Genre(R.string.male_genre, R.drawable.ic_male)
            "female" -> return Genre(R.string.female_genre, R.drawable.ic_female)
            else -> return Genre(R.string.other_genre, R.drawable.ic_other)
        }
    }
}