package com.adamstyrc.database

import android.content.Context
import com.adamstyrc.models.DEFAULT_LOCALE
import com.adamstyrc.models.RepositoryResult
import com.adamstyrc.models.Song
import com.adamstyrc.models.SongsRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.util.*
import kotlin.collections.ArrayList

class LocalSongRepository(
    applicationContext: Context
) : SongsRepository {

    private val songs: List<Song>

    init {
        songs = provideSongsFromJson(applicationContext)
    }

    private fun provideSongsFromJson(applicationContext: Context): List<Song> {
        val jsonString = getJsonFromAssets("songs-list.json", applicationContext)
        val listType = object : TypeToken<ArrayList<SongJsonEntity>>() {}.type
        val songEntities = Gson().fromJson<ArrayList<SongJsonEntity>>(jsonString, listType)
        return songEntities
            .filter { it.name != null && it.artist != null }
            .map { it.toSong() }
    }

    override fun getSongs(name: String): Observable<RepositoryResult<List<Song>>> {
        val nameLowerCase = name.toLowerCase(DEFAULT_LOCALE)
        return Observable.just(songs)
            .map { songs -> songs.filter {
                it.name.toLowerCase(DEFAULT_LOCALE).contains(nameLowerCase)
                        || it.artist.toLowerCase(DEFAULT_LOCALE).contains(nameLowerCase)
            }}
            .map { songs -> RepositoryResult.Success(songs) as RepositoryResult<List<Song>>}
            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun getJsonFromAssets(filename: String, context: Context): String {
        val manager = context.assets
        val file = manager.open(filename)
        val formArray = ByteArray(file.available())
        file.read(formArray)
        file.close()

        return String(formArray)
    }
}
