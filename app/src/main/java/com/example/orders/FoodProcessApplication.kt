package com.example.orders

import androidx.multidex.MultiDexApplication
import com.example.orders.module.initKoin

class FoodProcessApplication: MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}