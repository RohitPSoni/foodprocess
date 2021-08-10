package com.example.orders.ui.orderdetail.viewmodel

import android.os.CountDownTimer
import android.util.Log
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.orders.ext.calculateDifferenceInMinutes
import com.example.orders.repository.AcceptOrderRepo
import com.example.orders.ui.orderdetail.data.DetailOrderData
import java.util.concurrent.TimeUnit

class OrderDetailViewModel(private val repository: AcceptOrderRepo) : ViewModel() {

    val loading = ObservableField(View.VISIBLE)
    val data = MutableLiveData<DetailOrderData?>()
    val progress = ObservableField(0)
    private var timer: CountDownTimer? = null

    fun getDetailData(orderid: Int) {
        repository.getDetailOrder<DetailOrderData?>(orderid, {
            it?.let {
                data.postValue(it)
                startTimer(it.startTime)
                progress.set(View.GONE)
            }
        }, {

        })
    }

    private fun startTimer(createAt: Long) {
        timer?.cancel()
        timer = object : CountDownTimer(createAt, TimeUnit.MINUTES.toMillis(1)) {
            override fun onTick(millisUntilFinished: Long) {
                updateProgress(createAt)
            }

            override fun onFinish() {
                timer?.cancel()
            }
        }.start()
    }

    private fun updateProgress(timeout: Long) {
        val difference = calculateDifferenceInMinutes(timeout)
        Log.e("fatal detail", "$difference")
        progress.set(difference)
        if (difference <= 0) {
            timer?.cancel()
        }
    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }
}