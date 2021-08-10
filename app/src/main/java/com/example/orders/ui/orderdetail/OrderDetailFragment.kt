package com.example.orders.ui.orderdetail

import androidx.lifecycle.ViewModel
import com.example.orders.R
import com.example.orders.base.BaseFragment
import com.example.orders.databinding.FragmentOrderDetailBinding
import com.example.orders.ui.acceptorder.AcceptOrderFragment
import com.example.orders.ui.orderdetail.adapter.OrderDetailAdapter
import com.example.orders.ui.orderdetail.viewmodel.OrderDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class OrderDetailFragment : BaseFragment<FragmentOrderDetailBinding>() {

    private val viewModel: OrderDetailViewModel by viewModel()

    override fun getViewModel(): ViewModel? = viewModel

    override fun getLayoutId() = R.layout.fragment_order_detail

    override fun bindData() {
        val orderID = arguments?.getInt(AcceptOrderFragment.ORDER_ID_EXTRA) ?: 0
        val adapter = OrderDetailAdapter()
        viewBinder.itemsList.adapter = adapter
        viewModel.data.observe(viewLifecycleOwner, {
            it?.let { detailData ->
                detailData.list?.let { list ->
                    adapter.addList(list)
                }
            }
        })
        viewModel.getDetailData(orderid = orderID)
    }
}