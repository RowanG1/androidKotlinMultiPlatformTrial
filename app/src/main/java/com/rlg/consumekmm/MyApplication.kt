package com.rlg.consumekmm

import android.app.Application
import com.rlg.play_kotlin_multi_plat.androidModule
import commonModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(androidModuleConsumer, androidModule, commonModule)
        }
    }
}
