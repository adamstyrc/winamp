package com.adamstyrc.winamp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.adamstyrc.winamp.ui.adapter.SongsAdapter
import com.adamstyrc.winamp.ui.model.Song
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        rvSongs.adapter = SongsAdapter(
            this, arrayListOf(
                Song("Run to the Hills", "Iron Maiden", 183),
                Song("2 Minutes to Midnight", "Iron Maiden", 218),
                Song("Run to the Hills", "Iron Maiden", 183),
                Song("2 Minutes to Midnight", "Iron Maiden", 218),
                Song("Run to the Hills", "Iron Maiden", 183),
                Song("2 Minutes to Midnight", "Iron Maiden", 218),
                Song("Run to the Hills", "Iron Maiden", 183),
                Song("2 Minutes to Midnight", "Iron Maiden", 218),
                Song("Run to the Hills", "Iron Maiden", 183),
                Song("2 Minutes to Midnight", "Iron Maiden", 218),
                Song("Run to the Hills", "Iron Maiden", 183),
                Song("2 Minutes to Midnight", "Iron Maiden", 218),
                Song("Run to the Hills", "Iron Maiden", 183),
                Song("2 Minutes to Midnight", "Iron Maiden", 218),
                Song("Run to the Hills", "Iron Maiden", 183),
                Song("2 Minutes to Midnight", "Iron Maiden", 218),
                Song("Run to the Hills", "Iron Maiden", 183),
                Song("2 Minutes to Midnight", "Iron Maiden", 218),
                Song("Run to the Hills", "Iron Maiden", 183),
                Song("2 Minutes to Midnight", "Iron Maiden", 218),
                Song("Run to the Hills", "Iron Maiden", 183),
                Song("2 Minutes to Midnight", "Iron Maiden", 218),
                Song("Run to the Hills", "Iron Maiden", 183),
                Song("2 Minutes to Midnight", "Iron Maiden", 218),
                Song("Run to the Hills", "Iron Maiden", 183),
                Song("2 Minutes to Midnight", "Iron Maiden", 218)
            )
        )

    }
}
