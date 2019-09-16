package com.example.mvvmbasicprep

import android.app.Application
import com.facebook.stetho.Stetho
import timber.log.Timber

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG){
            //初始化Timber
            Timber.plant(Timber.DebugTree())
            Stetho.initializeWithDefaults(this)
        }
    }
}