package com.adamstyrc.winamp.di

import com.adamstyrc.api.ITunesSongsRepository
import com.adamstyrc.api.ITunesWebService
import com.adamstyrc.database.LocalSongRepository
import com.adamstyrc.winamp.viewmodel.DashboardViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    single { ITunesWebService(get()).api }
    single { ITunesSongsRepository(get()) }
    single { LocalSongRepository(get()) }
}

val mvvmModule = module {
    viewModel { DashboardViewModel(get(), get ()) }
}

