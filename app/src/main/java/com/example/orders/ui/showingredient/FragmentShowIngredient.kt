package com.example.orders.ui.showingredient

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.orders.R
import com.example.orders.base.BaseFragment
import com.example.orders.databinding.FragmentShowIngredientBinding
import com.example.orders.ui.showingredient.adapter.ShowIngredientAdapter
import com.example.orders.ui.showingredient.viewmodel.ShowIngredientViewModel
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayout.Tab
import com.jakewharton.rxbinding.widget.RxTextView
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class FragmentShowIngredient : BaseFragment<FragmentShowIngredientBinding>() {

    private val viewModel: ShowIngredientViewModel by viewModel()
    override fun getViewModel(): ViewModel? = viewModel

    override fun getLayoutId() = R.layout.fragment_show_ingredient

    override fun bindData() {
        RxTextView.textChanges(viewBinder.search)
            .filter { charSequence: CharSequence -> charSequence.length > 2 }
            .debounce(300, TimeUnit.MILLISECONDS)
            .map { charSequence: CharSequence -> charSequence.toString() }
            .subscribe { string ->
                Log.d("FragmentShowIngredient", "debounced $string")
                viewModel.getIngredient(
                    id = viewBinder.tabs.selectedTabPosition + 1,
                    searchChar = string
                )
            }

        viewBinder.tabs.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: Tab?) {
                viewModel.getIngredient(
                    id = (tab?.position ?: 0) + 1,
                    searchChar = viewBinder.search.text.toString()
                )
            }

            override fun onTabUnselected(tab: Tab?) {
            }

            override fun onTabReselected(tab: Tab?) {
            }
        })
        val adapter = ShowIngredientAdapter()
        viewBinder.recyclerItems.adapter = adapter
        viewModel.list.observe(viewLifecycleOwner, {
            adapter.updateList(it)
        })
    }
}