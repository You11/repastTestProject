package ru.you11.repasttestproject

import android.app.Application
import android.content.Context

class App: Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: App? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

}