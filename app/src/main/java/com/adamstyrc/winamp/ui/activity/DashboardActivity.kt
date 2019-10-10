package com.adamstyrc.winamp.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.adamstyrc.winamp.R
import com.adamstyrc.winamp.SongsSource
import com.adamstyrc.winamp.ui.adapter.SongsAdapter
import com.adamstyrc.winamp.viewmodel.DashboardViewModel
import com.miguelcatalan.materialsearchview.MaterialSearchView
import kotlinx.android.synthetic.main.activity_dashboard.*
import org.koin.android.viewmodel.ext.android.viewModel

class DashboardActivity : AppCompatActivity() {

    private val viewModel: DashboardViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        setSupportActionBar(tbDashboard)

        vSearch.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // TODO wait for user to stop typing (optimisation: saves requests count)
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
