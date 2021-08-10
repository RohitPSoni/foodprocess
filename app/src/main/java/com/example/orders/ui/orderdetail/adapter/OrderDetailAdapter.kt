package com.example.orders.ui.orderdetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.example.orders.databinding.ItemShowExtraBinding
import com.example.orders.ui.orderdetail.adapter.OrderDetailAdapter.OrderDetailViewHolder
import com.example.orders.ui.orderdetail.data.ShowDetailOrder

class OrderDetailAdapter : RecyclerView.Adapter<OrderDetailViewHolder>() {

    private val list = mutableListOf<ShowDetailOrder>()

    inner class OrderDetailViewHolder(val extraItemView: ItemShowExtraBinding) :
        RecyclerView.ViewHolder(extraItemView.root) {

        fun bind(detailOrder: ShowDetailOrder) {
            with(extraItemView) {
                setVariable(BR.acceptOrder, detailOrder)
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderDetailViewHolder =
        OrderDetailViewHolder(
            ItemShowExtraBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: OrderDetailViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    fun addList(list: List<ShowDetailOrder>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }
}