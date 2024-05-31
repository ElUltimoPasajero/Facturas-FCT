package com.example.facturas_tfc.viewmodel

import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.remoteconfig.FirebaseRemoteConfig


class RemoteConfigViewModel : ViewModel() {

    private val firebaseRemoteConfig: FirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()

    private val _switchVisibility = MutableLiveData<Boolean>()
    private val _darkThemeEnabled = MutableLiveData<Boolean>()

    val switchVisibility: LiveData<Boolean>
        get() = _switchVisibility

    val changeAppTheme: LiveData<Boolean>
        get() = _darkThemeEnabled

    init {
        fetchConfigurations()
    }

    private fun fetchConfigurations() {
        firebaseRemoteConfig.fetchAndActivate()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val visible = firebaseRemoteConfig.getBoolean("toggle_buttons_visibility")
                    _switchVisibility.postValue(visible)

                    val changeTheme = firebaseRemoteConfig.getBoolean("change_theme")
                    _darkThemeEnabled.postValue(changeTheme)

                    Log.d("RemoteConfig", "Fetch and activate successful")
                } else {
                    Log.d("RemoteConfig", "Fetch and activate failed")
                }
            }
    }

    fun applyTheme(darkThemeEnabled: Boolean) {
        if (darkThemeEnabled) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}
