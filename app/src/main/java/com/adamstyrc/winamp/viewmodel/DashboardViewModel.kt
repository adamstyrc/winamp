package com.adamstyrc.winamp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adamstyrc.models.RepositoryResult
import com.adamstyrc.models.Song
import com.adamstyrc.api.ITunesApi
import com.adamstyrc.api.ITunesSongsRepository
import io.reactivex.rxkotlin.subscribeBy

class DashboardViewModel : ViewModel() {

    private val loading =  MutableLiveData<Boolean>()
        .apply { value = false }
    private val songs = MutableLiveData<List<com.adamstyrc.models.Song>>()

    lateinit var api: ITunesApi

    fun getSongs() : LiveData<List<com.adamstyrc.models.Song>> = songs

    fun isLoading() : LiveData<Boolean> = loading

    fun searchSongs(searchText: String) {
        loading.postValue(true)
        ITunesSongsRepository(api)
            .getSong(searchText)
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


}
