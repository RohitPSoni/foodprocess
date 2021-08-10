package com.example.orders.ui.acceptorder.data

data class ShowOrder(
    val id: Int,
    val orderTitle: String,
    val quantity: Int,
    val createdAt: Long,
    val triggeredAt: Long
)