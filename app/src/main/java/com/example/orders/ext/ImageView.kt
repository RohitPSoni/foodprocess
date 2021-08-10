package com.example.orders.ext

import android.widget.ImageView
import com.example.orders.R
import com.example.orders.module.GlideApp

fun ImageView.clear() {
    GlideApp.with(context).clear(this)
}

fun ImageView.load(url: String?) {
    GlideApp.with(context)
        .load(url)
        .override(400, 400)
        .placeholder(R.drawable.ic_launcher_background)
        .into(this)
}