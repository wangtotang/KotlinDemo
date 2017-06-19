package com.martintong.kotlindemo

import android.app.Application

/**
🐶 @author  Martin_Tong
🐶 @date    2017/6/16
🐶
🐶 @apiNote
 */
class App : Application() {

    companion object {
        private var app: App? = null
        val instance by lazy {
            app!!
        }
    }

    override fun onCreate() {
        super.onCreate()
        app = this
    }

}