package com.adamstyrc.models

import io.reactivex.Observable

interface SongsRepository {
    fun getSongs(name: String): Observable<RepositoryResult<List<Song>>>
}
