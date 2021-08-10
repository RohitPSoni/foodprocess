package com.example.orders.network

import com.example.orders.network.ingridients.Ingridients
import com.example.orders.network.orders.Orders
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("assets/orders.json")
    fun getOrders() : Single<Orders>

    @GET("assets/ingridients.json")
    fun getIngridients(
        @Query("catId") id: Int,
        @Query("search") search: String
    ): Single<Ingridients>
}