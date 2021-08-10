package com.example.orders.mapper

import com.example.orders.network.ingridients.Info
import com.example.orders.ui.showingredient.data.ItemShowIngredients

interface IngridientsMapper : Function3<List<Info>, String, Int, List<ItemShowIngredients>>

class IngridientsMapperImpl : IngridientsMapper {

    override fun invoke(p1: List<Info>, p2: String, p3: Int): List<ItemShowIngredients> {
        val list = mutableListOf<ItemShowIngredients>()

        val filteredList = p1.asSequence().filter {
            it.catId == p3 && it.title.contains(p2, ignoreCase = true)
        }

        filteredList.forEach {
            val item = ItemShowIngredients(
                url = it.url,
                title = it.title,
                count = it.quantity
            )
            list.add(item)
        }
        return list
    }
}