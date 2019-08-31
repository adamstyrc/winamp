package com.adamstyrc.database

import com.adamstyrc.models.Song
import com.google.gson.annotations.SerializedName

data class SongJsonEntity(
    @SerializedName("Song Clean") val name: String?,
    @SerializedName("ARTIST CLEAN") val artist: String?,
    @SerializedName("Release Year") val releaseYear: String?
) {
    fun toSong() = Song(name!!, artist!!, releaseYear)
}
