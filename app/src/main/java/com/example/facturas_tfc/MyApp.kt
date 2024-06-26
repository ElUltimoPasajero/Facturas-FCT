package com.example.facturas_tfc

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()
        context = applicationContext


        RemoteConfigManager.initialize(this)

    }

    companion object {
        lateinit var context: Context
    }
}