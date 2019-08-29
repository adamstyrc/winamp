package com.adamstyrc.api

import com.adamstyrc.RepositoryResult
import com.adamstyrc.Song
import com.adamstyrc.SongsRepository
import io.reactivex.Observable

class ITunesSongsRepository(
    private val iTunesApi: ITunesApi
) : SongsRepository {

    override fun getSong(name: String): Observable<RepositoryResult<List<Song>>> {
        return iTunesApi.getSearch(name)
            .map { songsResponse ->
                if (songsResponse.isSuccessful) {
                    val body = songsResponse.body()!!
                    RepositoryResult.Success<List<Song>>(body.toSongs())
                } else {
                    RepositoryResult.Failure<List<Song>>(errorCode = songsResponse.code())
                }
            }
            .onErrorReturn { e -> RepositoryResult.Failure(errorException = e) }
    }
}
