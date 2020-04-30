package com.example.inspiringpersonroom.helpers

import android.content.Context

class PreferenceManager {
    companion object{
        const val PREFS_FILE = "InspiringPersonsPref"
        const val PREFS_KEY_FIRST_TIME = "FirstTime"
    }

    fun saveFirstTime(){
        val sharedPreferences = InspiringPersonApp.ApplicationContext.getSharedPreferences(
            PREFS_FILE,Context.MODE_PRIVATE
        )
        sharedPreferences.edit().putBoolean(PREFS_KEY_FIRST_TIME,false).apply()
    }
    fun isFirstTime():Boolean{
        val sharedPreferences = InspiringPersonApp.ApplicationContext.getSharedPreferences(
            PREFS_FILE,Context.MODE_PRIVATE
        )
        return sharedPreferences.getBoolean(PREFS_KEY_FIRST_TIME,true)
    }
}