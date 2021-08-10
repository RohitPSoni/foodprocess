package com.example.orders.base

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    val error = MutableLiveData<String>()

    val loading = ObservableField(View.VISIBLE)

    open fun hideLoader() {
        loading.set(View.GONE)
    }
}