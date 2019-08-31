package com.adamstyrc.winamp

import android.app.Application
import com.adamstyrc.winamp.di.mainModule
import com.adamstyrc.winamp.di.mvvmModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WinampApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@WinampApplication)
            modules(listOf(mainModule, mvvmModule))
        }
    }
}
