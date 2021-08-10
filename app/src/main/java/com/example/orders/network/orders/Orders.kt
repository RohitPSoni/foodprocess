package com.example.orders.network.orders

import com.google.gson.annotations.SerializedName

data class Orders(
    @SerializedName("status") val status: Status,
    @SerializedName("data") val data: List<Info>
)

data class Status(
    @SerializedName("success") val success: Boolean,
    @SerializedName("statusCode") val code: Int,
    @SerializedName("message") val message: String
)

data class Info(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("quantity") val quantity: Int,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("alerted_at") val alertedAt: String,
    @SerializedName("expired_at") val expiredAt: String?,
    @SerializedName("addon") val addOn: List<AddOns>?
)

data class AddOns(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("quantity") val quantity: Int
)