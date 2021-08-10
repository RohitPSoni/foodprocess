package com.example.orders.module

import com.example.orders.FoodProcessApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

fun FoodProcessApplication.initKoin(){
    startKoin {
        androidContext(applicationContext)
        modules(
            listOf(
                netModule,
                repoModule,
                provideMapper,
                viewModelModule
            )
        )
    }
}
