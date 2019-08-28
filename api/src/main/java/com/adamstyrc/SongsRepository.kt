package com.adamstyrc

import io.reactivex.Observable

interface SongsRepository {
    fun getSong(name: String): Observable<RepositoryResult<List<Song>>>
}
