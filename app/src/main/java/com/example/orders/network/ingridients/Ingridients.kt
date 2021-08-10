package com.example.orders.network.ingridients

import com.example.orders.network.orders.Status
import com.google.gson.annotations.SerializedName

data class Ingridients (@SerializedName("status") val status: Status,
                   @SerializedName("data") val data: List<Info>)

data class Info(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("category_id") val catId: Int,
    @SerializedName("quantity") val quantity: Int,
    @SerializedName("url") val url: String?
)