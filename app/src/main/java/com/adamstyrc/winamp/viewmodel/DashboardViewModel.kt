package com.adamstyrc.winamp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adamstyrc.api.ITunesSongsRepository
import com.adamstyrc.database.LocalSongRepository
import com.adamstyrc.models.SongsRepository
import com.adamstyrc.winamp.SongsSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposables
import io.reactivex.rxkotlin.subscribeBy

class DashboardViewModel(
    private val iTunesSongsRepository: ITunesSongsRepository,
    private val localSongRepository: LocalSongRepository
) : ViewModel() {

    private val loading =  MutableLiveData<Boolean>()
        .apply { value = false }
    private val songs = MutableLiveData<List<com.adamstyrc.models.Song>>()
    private val songsSource = MutableLiveData<SongsSource>()
        .apply { value = SongsSource.LOCAL }

    private var disposable = Disposables.disposed()
    private var currentRepository: SongsRepository = localSongRepository

    fun getSongs() : LiveData<List<com.adamstyrc.models.Song>> = songs
    fun isLoading() : LiveData<Boolean> = loading
    fun getSongsSource() : LiveData<SongsSource> = songsSource

    fun searchSongs(searchText: String) {
        loading.postValue(true)
        disposable.dispose()
        disposable = currentRepository
            .getSongs(searchText)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
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

        searchSongs("")
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}
