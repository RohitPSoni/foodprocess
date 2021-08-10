package com.example.orders.repository

import com.example.orders.mapper.IngridientsMapper
import com.example.orders.mock
import com.example.orders.network.Api
import com.example.orders.network.ingridients.Ingridients
import com.example.orders.network.orders.Orders
import com.example.orders.parseResource
import com.example.orders.ui.showingredient.data.ItemShowIngredients
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class ShowIngredientRepositoryTest {

    private val api: Api = mock()
    private lateinit var repo: ShowIngredientRepository
    private val mapper: IngridientsMapper = mock()
    private val ingridients: Ingridients = parseResource("ingridients.json")

    private val catID= 1
    private val searchString = ""

    @BeforeEach
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        repo = ShowIngredientRepositoryImpl(api, mapper)
    }

    @Test
    fun `get ingridients failed`(){
        val failedStatus = ingridients.status.copy(success = false)
        val failed = ingridients.copy(status = failedStatus)
        var failedOrderValue: Boolean
        var successValue: Boolean
        Mockito.`when`(api.getIngridients(catID, searchString)).thenReturn(Single.just(failed))
        repo.getIngredient<List<ItemShowIngredients>>(catID, searchString, {
            successValue = true
            assertTrue(successValue)
        }, {
            failedOrderValue = true
            assertTrue(failedOrderValue)
        })
    }

    @Test
    fun `get ingridients passed`(){
        var failedOrderValue: Boolean
        var successValue: Boolean
        Mockito.`when`(api.getIngridients(catID, searchString)).thenReturn(Single.just(ingridients))
        repo.getIngredient<List<ItemShowIngredients>>(catID, searchString, {
            successValue = true
            assertTrue(successValue)
            assertEquals(it.size, ingridients.data.size)
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