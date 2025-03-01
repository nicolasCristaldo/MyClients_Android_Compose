package com.nicolascristaldo.myclients.data.providers

import com.nicolascristaldo.myclients.R

/**
 * Data class that represents a genre.
 * @property nameRes The resource ID for the genre name.
 * @property imageRes The resource ID for the genre image.
 */
data class Genre(
    val nameRes: Int,
    val imageRes: Int
)

/**
 * Object that provides a list of genres.
 */
object GenreProvider {
    /**
     * List of available genres.
     */
    val genres = listOf(
        "male",
        "female",
        "other"
    )

    /**
     * Returns a genre by its name.
     * @param name The name of the genre.
     * @return The corresponding [Genre] object.
     */
    fun getGenreByName(name: String): Genre {
        when(name) {
            "male" -> return Genre(R.string.male_genre, R.drawable.ic_male)
            "female" -> return Genre(R.string.female_genre, R.drawable.ic_female)
            else -> return Genre(R.string.other_genre, R.drawable.ic_other)
        }
    }
}