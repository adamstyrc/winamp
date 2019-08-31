package com.adamstyrc.winamp.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.adamstyrc.api.ITunesSongsRepository
import com.adamstyrc.api.ITunesWebService
import com.adamstyrc.database.LocalSongRepository
import com.adamstyrc.winamp.R
import com.adamstyrc.winamp.SongsSource
import com.adamstyrc.winamp.ui.adapter.SongsAdapter
import com.adamstyrc.winamp.viewmodel.DashboardViewModel
import com.miguelcatalan.materialsearchview.MaterialSearchView
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {

    private lateinit var viewModel: DashboardViewModel
    private lateinit var sourceAdapter: ArrayAdapter<SongsSource>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        setSupportActionBar(tbDashboard)

        viewModel = ViewModelProviders.of(this)
            .get(DashboardViewModel::class.java)

        viewModel.iTunesSongsRepository = ITunesSongsRepository(
            ITunesWebService(applicationContext).api
        )
        viewModel.localSongRepository = LocalSongRepository(applicationContext)
        viewModel.setSongsSource(SongsSource.LOCAL)

        vSearch.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.searchSongs(newText)
                return true
            }
        })

        viewModel.getSongs().observe(this, Observer { songs ->
            if (songs != null && songs.isNotEmpty()) {
                rvSongs.adapter = SongsAdapter(songs)
                tvNoSongs.visibility = View.GONE
            } else {
                rvSongs.adapter = SongsAdapter(emptyList())
                tvNoSongs.visibility = View.VISIBLE
            }
        })

        viewModel.isLoading().observe(this, Observer { loading ->
            if (loading == true) {
                pbLoadingIndicator.visibility = View.VISIBLE
            } else {
                pbLoadingIndicator.visibility = View.GONE
            }
        })

        setSongsSourceView()
    }

    private fun setSongsSourceView() {
        sourceAdapter = ArrayAdapter(
            this,
            R.layout.item_source,
            R.id.tvSource,
            SongsSource.values()
        )
        sSource.adapter = sourceAdapter

        viewModel.getSongsSource().observe(this, Observer { songsSource ->
            sSource.setSelection(songsSource.ordinal)
        })

        sSource.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                sourceAdapter.getItem(position)?.let { songsSource ->
                    viewModel.setSongsSource(songsSource)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)

        menu?.findItem(R.id.action_search)?.let { searchItem ->
            vSearch.setMenuItem(searchItem)
        }

        return true
    }
}
