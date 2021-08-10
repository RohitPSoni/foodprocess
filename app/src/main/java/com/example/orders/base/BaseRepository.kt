package com.example.orders.base

import io.reactivex.rxjava3.disposables.Disposable

abstract class BaseRepository {

    open var disposable: Disposable? = null

    open fun dispose() {
        disposable?.let { if (it.isDisposed.not()) it.dispose() }
    }
}