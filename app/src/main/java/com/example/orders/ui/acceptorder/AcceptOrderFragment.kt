package com.example.orders.ui.acceptorder

import android.media.MediaPlayer
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.orders.R
import com.example.orders.base.BaseFragment
import com.example.orders.databinding.FragmentAcceptOrderBinding
import com.example.orders.ui.acceptorder.adapter.ShowOrderAdapter
import com.example.orders.ui.acceptorder.adapter.ShowOrderAdapter.ClickListener
import com.example.orders.ui.acceptorder.viewmodel.ShowOrderItemViewModel
import com.example.orders.ui.acceptorder.viewmodel.ShowOrderViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AcceptOrderFragment : BaseFragment<FragmentAcceptOrderBinding>() {

    companion object {

        const val ORDER_ID_EXTRA = "ORDER ID EXTRA"
    }

    private val viewModel: ShowOrderViewModel by viewModel()

    override fun getViewModel(): ViewModel? = viewModel

    override fun getLayoutId() = R.layout.fragment_accept_order

    private val adapter = ShowOrderAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observeData()
        viewModel.getOrders()

    }

    override fun bindData() {
        viewBinder.orderList.adapter = adapter

        with(adapter) {
            attachListener(object : ClickListener {
                override fun onButtonClicked(expired: Boolean, position: Int) {
                    removeItem(position)
                    if (expired.not()) {
                        context?.let {
                            val mediaPlayer = MediaPlayer.create(it, R.raw.order_accepted)
                            mediaPlayer.start()
                        }
                    }
                }

                override fun onItemClicked(position: Int, orderId: Int) {
                    val bundle = Bundle().apply {
                        putInt(ORDER_ID_EXTRA, orderId)
                    }
                    findNavController().navigate(
                        R.id.action_showOrder_to_orderDetailFragment,
                        bundle
                    )
                }
            })
        }
    }

    private fun observeData() {
        viewModel.list.observe(this, {
            val list = mutableListOf<ShowOrderItemViewModel>()
            it.forEach { order ->
                val orderItem = ShowOrderItemViewModel()
                orderItem.itemData.value = order
                list.add(orderItem)
            }
            adapter.showData(list)
        })
    }
}