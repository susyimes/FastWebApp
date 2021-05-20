package com.susyimes.fastwebapp

import android.app.Application

class App :Application() {

    override fun onCreate() {
        super.onCreate()
        ObjectBox.init(this)
    }
}