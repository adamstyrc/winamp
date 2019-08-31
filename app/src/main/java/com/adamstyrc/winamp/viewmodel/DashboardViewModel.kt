package com.adamstyrc.winamp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adamstyrc.api.ITunesSongsRepository
import com.adamstyrc.database.LocalSongRepository
import com.adamstyrc.models.SongsRepository
import com.adamstyrc.winamp.SongsSource
import io.reactivex.rxkotlin.subscribeBy

class DashboardViewModel : ViewModel() {

    private val loading =  MutableLiveData<Boolean>()
        .apply { value = false }
    private val songs = MutableLiveData<List<com.adamstyrc.models.Song>>()

    lateinit var iTunesSongsRepository: ITunesSongsRepository
    lateinit var localSongRepository: LocalSongRepository
    private var currentRepository: SongsRepository? = null

    fun getSongs() : LiveData<List<com.adamstyrc.models.Song>> = songs

    fun isLoading() : LiveData<Boolean> = loading

    fun searchSongs(searchText: String) {
        loading.postValue(true)
        currentRepository
            ?.getSongs(searchText)
            ?.subscribeBy(
                onNext = { result ->
                    if (result is com.adamstyrc.models.RepositoryResult.Success) {
                        songs.postValue(result.body)
                    } else {
                        songs.postValue(null)
                    }
                    loading.postValue(false)
                },
                onError = {
                    songs.postValue(null)
                    loading.postValue(false)
                }
            )
    }

    fun setSongsSource(songsSource: SongsSource) {
        when (songsSource) {
            SongsSource.REMOTE -> currentRepository = iTunesSongsRepository
            SongsSource.LOCAL -> currentRepository = localSongRepository
        }
    }
}
