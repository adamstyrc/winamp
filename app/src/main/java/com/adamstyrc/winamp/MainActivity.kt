package com.adamstyrc.winamp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.adamstyrc.RepositoryResult
import com.adamstyrc.api.ITunesApi
import com.adamstyrc.api.ITunesSongsRepository
import com.adamstyrc.api.ITunesWebService
import com.adamstyrc.winamp.ui.adapter.SongsAdapter
import com.adamstyrc.winamp.ui.model.Song
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var api: ITunesApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        api = ITunesWebService(applicationContext).api
    }

    override fun onResume() {
        super.onResume()

        ITunesSongsRepository(api)
            .getSong("a")
            .subscribeBy(
                onNext = { result ->
                    if (result is RepositoryResult.Success) {
                        rvSongs.adapter = SongsAdapter(
                            this, result.body)
                    }
                },
                onError = {
                    it.toString()
                }
            )
    }
}
