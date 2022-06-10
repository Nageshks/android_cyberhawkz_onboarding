package com.nageshempire.cybersecurityonboarding.util

import android.annotation.SuppressLint
import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

@SuppressLint("StaticFieldLeak")
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        dataStore = applicationContext.appConfiguration
    }

    companion object{
        private var dataStore: DataStore<Preferences>? = null
        val configuration get() = dataStore!!
    }
}