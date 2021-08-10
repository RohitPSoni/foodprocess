package com.example.orders.mapper

import com.example.orders.network.orders.Orders
import com.example.orders.parseResource
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DetailOrderMapperTest {

    private val orders: Orders = parseResource("orders.json")
    private lateinit var mapper: DetailOrderMapper

    @BeforeEach
    fun setUp() {
        mapper = DetailOrderMapperImpl()
    }

    @Test
    fun `get detail order `() {

        val listOrders = orders.data.first {
            it.id == 10
        }.addOn

        val detail = mapper.invoke(orders, 10)
        listOrders?.let {
            assertEquals(it.size, detail.list!!.size)
        }?:run {
            assertNull(detail.list)
        }
    }
}