package com.example.inspiringpersonroom.helpers

import android.app.Application
import android.content.Context

class InspiringPersonApp : Application() {
    companion object{
        lateinit var ApplicationContext : Context
        private set
    }

    override fun onCreate() {
        super.onCreate()
        ApplicationContext = this
    }
}