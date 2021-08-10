package com.example.orders.repository

import com.example.orders.base.BaseRepository
import com.example.orders.mapper.IngridientsMapper
import com.example.orders.network.Api
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

interface ShowIngredientRepository {

    fun <T> getIngredient(
        selectedTab: Int,
        searchKeyword: String,
        onSuccess: (T) -> Unit,
        onFailuare: (Throwable) -> Unit
    )
}

class ShowIngredientRepositoryImpl(
    private val api: Api,
    private val mapper: IngridientsMapper
) :
    ShowIngredientRepository, BaseRepository() {

    override fun <T> getIngredient(
        selectedTab: Int,
        searchKeyword: String,
        onSuccess: (T) -> Unit,
        onFailuare: (Throwable) -> Unit
    ) {
        disposable = api.getIngridients(id = selectedTab, search = searchKeyword)
            .subscribeOn(Schedulers.io())
            .map {
                if (it.status.success) {
                    mapper.invoke(it.data, searchKeyword, selectedTab)
                } else {
                    throw Exception(it.status.message)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess.invoke(it as T)
                dispose()
            }, {
                onFailuare.invoke(it)
                dispose()
            })
    }
}