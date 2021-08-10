package com.example.orders.mapper

import com.example.orders.network.orders.Orders
import com.example.orders.parseResource
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class OrdersMapperTest {

    private val orders: Orders = parseResource("orders.json")
    private lateinit var mapper: OrdersMapper

    @BeforeEach
    fun setUp() {
        mapper = OrdersMapperImpl()
    }

    @Test
    fun `map accept order data`(){
        val data = mapper.invoke(orders)
        assertEquals(data.size, orders.data.size)
    }
}