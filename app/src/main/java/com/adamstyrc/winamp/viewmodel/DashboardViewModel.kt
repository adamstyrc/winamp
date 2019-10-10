package com.adamstyrc.winamp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adamstyrc.api.ITunesSongsRepository
import com.adamstyrc.database.LocalSongRepository
import com.adamstyrc.models.RepositoryResult
import com.adamstyrc.models.Song
import com.adamstyrc.winamp.SongsSource
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposables
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.util.*

class DashboardViewModel(
    private val iTunesSongsRepository: ITunesSongsRepository,
    private val localSongRepository: LocalSongRepository
) : ViewModel() {

    private val loading = MutableLiveData<Boolean>()
        .apply { value = false }
    private val songs = MutableLiveData<List<Song>>()
    private val songsSource = MutableLiveData<SongsSource>()
        .apply { value = SongsSource.LOCAL }

    private var disposable = Disposables.disposed()

    private var repositories: List<SongsSource> = arrayListOf(
        SongsSource.LOCAL,
        SongsSource.REMOTE
    )

    init {
        searchSongs("")
    }

    fun getSongs(): LiveData<List<Song>> = songs

    fun isLoading(): LiveData<Boolean> = loading

    fun searchSongs(searchText: String) {
        loading.postValue(true)
        disposable.dispose()

        val observables =
            repositories
                .map {
                    when (it) {
                        SongsSource.LOCAL -> localSongRepository
                        SongsSource.REMOTE -> iTunesSongsRepository
                    }
                }
                .map {
                    it.getSongs(searchText)
                }
        disposable = Observables.zip(
            observables[0],
            observables[1],
            { localSongsResult, remoteSongsResult ->
                listOf(localSongsResult, remoteSongsResult)
            })
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = { results ->
                    var fetchedSongs = arrayListOf<Song>()

                    results.forEach { result ->
                        if (result is RepositoryResult.Success) {
                            fetchedSongs.addAll(result.body)
                        }
                    }

                    if (fetchedSongs.isNotEmpty()) {
                        songs.postValue(fetchedSongs)
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
    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}
