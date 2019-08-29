package com.adamstyrc.winamp

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.adamstyrc.RepositoryResult
import com.adamstyrc.api.ITunesApi
import com.adamstyrc.api.ITunesSongsRepository
import com.adamstyrc.api.ITunesWebService
import com.adamstyrc.winamp.ui.adapter.SongsAdapter
import com.miguelcatalan.materialsearchview.MaterialSearchView
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var api: ITunesApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(tbDashboard)

        api = ITunesWebService(applicationContext).api

        vSearch.setOnQueryTextListener(object: MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)

        menu?.findItem(R.id.action_search)?.let { searchItem ->
            vSearch.setMenuItem(searchItem)
        }

        return true
    }

    override fun onResume() {
        super.onResume()

        ITunesSongsRepository(api)
            .getSong("a")
            .subscribeBy(
                onNext = { result ->
                    if (result is RepositoryResult.Success) {
                        rvSongs.adapter = SongsAdapter(this, result.body)
                    }
                },
                onError = {
                    it.toString()
                }
            )
    }
}
