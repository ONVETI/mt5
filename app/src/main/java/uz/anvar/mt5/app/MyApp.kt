package uz.anvar.mt5.app

import android.app.Application
import uz.anvar.mt5.di.initKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(applicationContext)
    }
}