package com.example.facturas_tfc

import android.app.Application
import android.content.Context
import com.example.facturas_tfc.di.myModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApp)
            modules(myModule)
        }

        context = applicationContext


        RemoteConfigManager.initialize(this)

    }

    companion object {
        lateinit var context: Context
    }
}