package com.example.orders.mapper

import com.example.orders.network.orders.Orders
import com.example.orders.ui.acceptorder.data.ShowOrder
import com.example.orders.ext.toLongDate

interface OrdersMapper : Function1<Orders, List<ShowOrder>>

class OrdersMapperImpl : OrdersMapper {

    override fun invoke(p1: Orders): List<ShowOrder> {
        val list = mutableListOf<ShowOrder>()
        p1.data.forEach {
            val order = ShowOrder(
                id = it.id,
                quantity = it.quantity,
                orderTitle = it.title,
                createdAt = it.createdAt.toLongDate(),
                triggeredAt = it.alertedAt.toLongDate()
            )
            list.add(order)
        }
        return list
    }
}
