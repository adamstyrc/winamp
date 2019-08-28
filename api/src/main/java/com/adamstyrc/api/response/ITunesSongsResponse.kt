package com.adamstyrc.api.response

import com.adamstyrc.Song

data class ITunesSongsResponse(
    var resultCount: Int?,
    var results: List<Result>?
) {

    fun toSongs() : List<Song> {
        if (results == null) {
            return emptyList()
        } else {
            return results!!.map {
//                if (it.trackName != null
//                    && it.artistName != null) {
                    return@map Song(it.trackName!!, it.artistName!!, 2020)
//                }
            }
        }

    }

    data class Result(
        var artistName: String?,
        var trackName: String?,
        var trackTimeMillis: Long?,
        var releaseDate: String?
    )
}
