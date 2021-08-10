package com.example.orders.ui.acceptorder.viewmodel

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import com.example.orders.R
import com.example.orders.base.BaseViewModel
import com.example.orders.ui.acceptorder.data.ShowOrder
import com.example.orders.repository.AcceptOrderRepo
import kotlinx.coroutines.launch
import java.util.Observable

class ShowOrderViewModel(
    private val repo: AcceptOrderRepo
) : BaseViewModel() {

    val list = MutableLiveData<List<ShowOrder>>()

    fun getOrders() {
        repo.acceptOrder<List<ShowOrder>>( {
            hideLoader()
            list.postValue(it)
        }, {
            error.postValue(it.localizedMessage)
        })
    }

    fun showIngridientClicked(view: View){
        Navigation.findNavController(view).navigate(R.id.action_showOrder_to_fragmentShowIngredient)
    }
}
