package com.example.orders.ui.showingredient.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.example.orders.databinding.ItemShowIngedrientBinding
import com.example.orders.ext.clear
import com.example.orders.ui.showingredient.adapter.ShowIngredientAdapter.ShowIngredientViewHolder
import com.example.orders.ui.showingredient.data.ItemShowIngredients

class ShowIngredientAdapter : RecyclerView.Adapter<ShowIngredientViewHolder>() {

    private val list = mutableListOf<ItemShowIngredients>()

    inner class ShowIngredientViewHolder(private val binder: ItemShowIngedrientBinding) :
        RecyclerView.ViewHolder(binder.root) {

        fun bindData(items: ItemShowIngredients) {
            with(binder) {
                binder.imv.clear()
                setVariable(BR.ingredientView, items)
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowIngredientViewHolder =
        ShowIngredientViewHolder(
            ItemShowIngedrientBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: ShowIngredientViewHolder, position: Int) {
        holder.bindData(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun updateList(newList: List<ItemShowIngredients>){
        this.list.clear()
        this.list.addAll(newList)
        notifyDataSetChanged()
    }
}