package com.example.orders.repository

import com.example.orders.mapper.DetailOrderMapper
import com.example.orders.mapper.OrdersMapper
import com.example.orders.mock
import com.example.orders.network.Api
import com.example.orders.network.orders.Orders
import com.example.orders.parseResource
import com.example.orders.ui.acceptorder.data.ShowOrder
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Test
import org.mockito.Mockito

class AcceptOrderRepoTest {

    private lateinit var repo: AcceptOrderRepo
    private val mapper: OrdersMapper = mock()
    private val orderDetailMaper: DetailOrderMapper = mock()
    private val api: Api = mock()
    private val orders: Orders = parseResource("orders.json")

    @BeforeEach
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        repo = AcceptOrderRepoImpl(api, mapper, orderDetailMaper)
    }

    @Test
    fun `when accept order is failed`() {
        val failedStatus = orders.status.copy(success = false)
        val failedOrder = orders.copy(status = failedStatus)
        var failedOrderValue: Boolean
        var successValue: Boolean
        Mockito.`when`(api.getOrders()).thenReturn(Single.just(failedOrder))
        repo.acceptOrder<List<ShowOrder>>({
            successValue = true
            assertTrue(successValue)
        }, {
            failedOrderValue = true
            assertTrue(failedOrderValue)
        })

    }

    @Test
    fun `when accept order detail is success`() {
        var failedOrderValue: Boolean
        var successValue: Boolean
        Mockito.`when`(api.getOrders()).thenReturn(Single.just(orders))
        repo.getDetailOrder<ShowOrder>(orderId = 10, {
            successValue = true
            assertTrue(successValue)
            val id = orders.data.first { info ->
                info.id == 10
            }
            assertEquals(it.id, id.id)
        }, {
            failedOrderValue = true
            assertTrue(failedOrderValue)
        })
    }

    @Test
    fun `when accept order detail is failed`() {
        val failedStatus = orders.status.copy(success = false)
        val failedOrder = orders.copy(status = failedStatus)
        var failedOrderValue: Boolean
        var successValue: Boolean
        Mockito.`when`(api.getOrders()).thenReturn(Single.just(failedOrder))
        repo.getDetailOrder<ShowOrder>(orderId = 10, {
            successValue = true
            assertTrue(successValue)
        }, {
            failedOrderValue = true
            assertTrue(failedOrderValue)
        })

    }

    @Test
    fun `when accept order is success`() {
        var failedOrderValue: Boolean
        var successValue: Boolean
        Mockito.`when`(api.getOrders()).thenReturn(Single.just(orders))
        repo.acceptOrder<List<ShowOrder>>({
            successValue = true
            assertTrue(successValue)
            assertEquals(it.size, orders.data.size)
        }, {
            failedOrderValue = true
            assertTrue(failedOrderValue)
        })
    }

    @AfterEach
    fun tearDown() {
        RxAndroidPlugins.reset()
    }
}