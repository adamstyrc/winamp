package com.adamstyrc.api.response

import com.adamstyrc.Song
import com.adamstyrc.extensions.year
import java.util.*

data class ITunesSongsResponse(
    var resultCount: Int?,
    var results: List<Result>?
) {

    fun toSongs() =
        if (results == null) {
            emptyList()
        } else {
            results!!.map {
                Song(
                    it.trackName!!,
                    it.artistName!!,
                    it.releaseDate?.year()
                )
            }
        }

    data class Result(
        var artistName: String?,
        var trackName: String?,
        var releaseDate: Date?
    )
}
