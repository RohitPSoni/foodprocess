package com.example.orders.ui.acceptorder.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.orders.BR
import com.example.orders.ui.acceptorder.adapter.ShowOrderAdapter.ShowOrderViewHolder
import com.example.orders.ui.acceptorder.viewmodel.ShowOrderItemViewModel
import com.example.orders.databinding.ViewAcceptOrderBinding

class ShowOrderAdapter : RecyclerView.Adapter<ShowOrderViewHolder>() {

    interface ClickListener {

        fun onButtonClicked(expired: Boolean, position: Int)

        fun onItemClicked(position: Int, orderId: Int)
    }

    private val listOrder = mutableListOf<ShowOrderItemViewModel>()
    private var listener: ClickListener? = null

    inner class ShowOrderViewHolder(
        private val binding: ViewAcceptOrderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        // private var timer: CountDownTimer? = null
        fun bind(showOrder: ShowOrderItemViewModel) {
            showOrder.startTimer()
            with(binding) {
                setVariable(BR.itemView, showOrder)
                lifecycleOwner = binding.lifecycleOwner
                executePendingBindings()
                acceptOrder.setOnClickListener {
                    listener?.onButtonClicked(showOrder.progress.get() ?: 0 <= 0, adapterPosition)
                    showOrder.acceptRejectedOrder()
                }
                root.setOnClickListener {
                    listener?.onItemClicked(
                        position = adapterPosition, orderId = showOrder.itemData.value!!.id
                    )
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowOrderViewHolder =
        ShowOrderViewHolder(
            ViewAcceptOrderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ShowOrderViewHolder, position: Int) {
        holder.bind(listOrder[position])
    }

    override fun getItemCount(): Int = listOrder.size

    fun showData(list: List<ShowOrderItemViewModel>) {
        listOrder.clear()
        listOrder.addAll(list)
        notifyDataSetChanged()
    }

    fun removeItem(index: Int) {
        listOrder.removeAt(index)
        notifyItemRemoved(index)
    }

    fun attachListener(listener: ClickListener) {
        this.listener = listener
    }

    override fun onViewRecycled(holder: ShowOrderViewHolder) {
        super.onViewRecycled(holder)
        listOrder[holder.adapterPosition].acceptRejectedOrder()
    }
}