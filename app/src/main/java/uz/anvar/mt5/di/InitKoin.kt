package uz.anvar.mt5.di

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

fun initKoin(
    context: Context,
) {
    startKoin {
        androidContext(context)
        modules(
//            appModule,
//            networkModule(BaseUrl("http://193.149.18.127/")),
            dataModule,
            screensModule,
        )
    }
}