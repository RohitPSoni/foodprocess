package com.example.orders.mapper

import com.example.orders.ext.toLongDate
import com.example.orders.ext.toTime
import com.example.orders.network.orders.Orders
import com.example.orders.ui.orderdetail.data.DetailOrderData
import com.example.orders.ui.orderdetail.data.ShowDetailOrder

interface DetailOrderMapper : Function2<Orders, Int, DetailOrderData>

class DetailOrderMapperImpl : DetailOrderMapper {

    override fun invoke(p1: Orders, orderId: Int): DetailOrderData {
        val info = p1.data.first {
            it.id == orderId
        }
        return with(info) {
            var list: MutableList<ShowDetailOrder>? = null
            addOn?.let { addOn ->
                list = mutableListOf()
                addOn.forEach {
                    val data = ShowDetailOrder(
                        title = it.title,
                        quantity = it.quantity
                    )
                    list!!.add(data)
                }
            }
            DetailOrderData(
                list = list,
                orderId = orderId,
                time = createdAt.toTime(),
                startTime = createdAt.toLongDate(),
                totalItem = list?.size ?: 0
            )
        }
    }
}