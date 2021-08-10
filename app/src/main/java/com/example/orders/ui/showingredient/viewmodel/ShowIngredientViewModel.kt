package com.example.orders.ui.showingredient.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.orders.base.BaseViewModel
import com.example.orders.repository.ShowIngredientRepository
import com.example.orders.ui.showingredient.data.ItemShowIngredients

class ShowIngredientViewModel(private val repository: ShowIngredientRepository) : BaseViewModel() {

    val list = MutableLiveData<List<ItemShowIngredients>>()
    fun getIngredient(id: Int, searchChar: String) {
        repository.getIngredient<List<ItemShowIngredients>>(id, searchChar,
            {
                list.postValue(it)
                hideLoader()
            }, {
                error.postValue(it.localizedMessage)
            })
    }
}