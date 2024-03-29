package com.adamstyrc.winamp.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.adamstyrc.models.Song
import com.adamstyrc.winamp.R

class SongsAdapter(
    private val items: List<Song>
) : RecyclerView.Adapter<SongsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_song, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currencyRate = items[position]
        holder.bindCurrencyRate(currencyRate, position)
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var tvSongIndex: TextView = itemView.findViewById(R.id.tvSongIndex)
        var tvSongName: TextView = itemView.findViewById(R.id.tvSongName)
        var tvSongDuration: TextView = itemView.findViewById(R.id.tvSongDuration)

        @SuppressLint("SetTextI18n")
        fun bindCurrencyRate(song: Song, position: Int) {
            tvSongIndex.text = "$position."
            tvSongName.text = "${song.name} - ${song.artist}"
            tvSongDuration.text = "${song.releaseYear}"
        }
    }
}
