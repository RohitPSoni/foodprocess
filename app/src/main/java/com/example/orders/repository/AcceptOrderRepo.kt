package com.example.orders.repository

import com.example.orders.base.BaseRepository
import com.example.orders.mapper.DetailOrderMapper
import com.example.orders.mapper.OrdersMapper
import com.example.orders.network.Api
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

interface AcceptOrderRepo {

    fun <T> acceptOrder(onSuccess: (T) -> Unit, onFailuare: (Throwable) -> Unit)

    fun <T> getDetailOrder(orderId: Int, onSuccess: (T) -> Unit, onFailuare: (Throwable) -> Unit)
}

class AcceptOrderRepoImpl(
    private val api: Api,
    private val mapper: OrdersMapper,
    private val orderDetailMaper: DetailOrderMapper,
) : AcceptOrderRepo, BaseRepository() {

    override fun <T> getDetailOrder(
        orderId: Int,
        onSuccess: (T) -> Unit,
        onFailuare: (Throwable) -> Unit
    ) {
        disposable = api.getOrders()
            .subscribeOn(Schedulers.io())
            .map {
                if (it.status.success) {
                    orderDetailMaper.invoke(it, orderId)
                } else {
                    throw Exception(it.status.message)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess.invoke(it as T)
                dispose()
            }, {
                onFailuare.invoke(it)
                dispose()
            })
    }

    override fun <T> acceptOrder(onSuccess: (T) -> Unit, onFailuare: (Throwable) -> Unit) {
        disposable = api.getOrders()
            .subscribeOn(Schedulers.io())
            .map {
                if (it.status.success) {
                mapper.invoke(it)
                } else {
                    throw Exception(it.status.message)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess.invoke(it as T)
                dispose()
            }, {
                onFailuare.invoke(it)
                dispose()
            })
    }
}
