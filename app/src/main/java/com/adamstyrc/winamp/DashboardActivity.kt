package com.adamstyrc.winamp

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.adamstyrc.api.ITunesWebService
import com.adamstyrc.winamp.ui.adapter.SongsAdapter
import com.adamstyrc.winamp.viewmodel.DashboardViewModel
import com.miguelcatalan.materialsearchview.MaterialSearchView
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {

    private lateinit var viewModel: DashboardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        setSupportActionBar(tbDashboard)

        viewModel = ViewModelProviders.of(this)
            .get(DashboardViewModel::class.java)

        viewModel.api = ITunesWebService(applicationContext).api

        vSearch.setOnQueryTextListener(object: MaterialSearchView.OnQueryTextListener {
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
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)

        menu?.findItem(R.id.action_search)?.let { searchItem ->
            vSearch.setMenuItem(searchItem)
        }

        return true
    }
}
