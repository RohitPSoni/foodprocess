package com.example.orders.ui.acceptorder.viewmodel

import android.os.CountDownTimer
import android.util.Log
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.orders.ext.calculateDifferenceInMinutes
import com.example.orders.ui.acceptorder.data.ShowOrder
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import java.util.concurrent.TimeUnit

class ShowOrderItemViewModel : ViewModel() {

    val itemData = MutableLiveData<ShowOrder>()
    val progress = ObservableField(0)
    val visibility = ObservableField(View.VISIBLE)

    private var timer: CountDownTimer? = null

    fun startTimer() {
        timer?.let {
            it.cancel()
        }
        val dirrerenceNow = itemData.value?.createdAt ?: System.currentTimeMillis()
        timer = object : CountDownTimer(dirrerenceNow, TimeUnit.MINUTES.toMillis(1)) {
            override fun onTick(millisUntilFinished: Long) {
                updateProgress(itemData.value?.createdAt ?: System.currentTimeMillis())
            }

            override fun onFinish() {
                timer?.cancel()
            }
        }.start()
    }

    fun acceptRejectedOrder(){
        timer?.cancel()
    }

    private fun updateProgress(timeout: Long) {
        val difference = calculateDifferenceInMinutes(timeout)
        Log.e("fatal", "$difference for Name ${itemData.value?.orderTitle}")
        progress.set(difference)
        if (difference <= 0) {
            timer?.cancel()
            visibility.set(View.GONE)
        }
    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }
}