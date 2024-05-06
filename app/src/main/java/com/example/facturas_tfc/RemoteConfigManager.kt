package com.example.facturas_tfc

import android.content.Context
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings

object RemoteConfigManager {

    private lateinit var firebaseRemoteConfig: FirebaseRemoteConfig

    fun initialize(context: Context) {
        firebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
        val configSettings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(0)
            .build()
        firebaseRemoteConfig.setConfigSettingsAsync(configSettings)
    }

    fun getFirebaseRemoteConfig(): FirebaseRemoteConfig {
        return firebaseRemoteConfig
    }

}