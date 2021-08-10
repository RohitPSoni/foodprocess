package com.example.orders.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.orders.BR

abstract class BaseFragment<VB : ViewDataBinding> : Fragment() {

    private var _viewBinder: VB? = null
    protected val viewBinder get() = _viewBinder!!

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewBinder?.let {} ?: run {
            _viewBinder = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        }
        getViewModel()?.let {
            with(viewBinder) {
                setVariable(BR.viewModel, it)
                lifecycleOwner = this@BaseFragment
            }
        }
        getViewModel()?.let { it ->
            if (it is BaseViewModel) {
                it.error.observe(viewLifecycleOwner, { error ->
                    showError(errorCause = error)
                    it.hideLoader()
                })
            }
        }
        bindData()
        return viewBinder.root
    }

    open fun bindData() {
    }

    abstract fun getViewModel(): ViewModel?

    @LayoutRes
    abstract fun getLayoutId(): Int

    override fun onDestroy() {
        super.onDestroy()
        _viewBinder = null
    }

    private fun showError(errorCause: String) {
        val errorDialog = ErrorDialogFragment().apply {
            arguments = bundleOf(Pair(ErrorDialogFragment.ERROR_MESSAGE_EXTRA, errorCause))
        }
        errorDialog.show(childFragmentManager, ErrorDialogFragment.TAG)
    }
}
