package com.example.orders.binder

import android.widget.ImageView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.orders.R
import com.example.orders.ext.load

@BindingAdapter("buttonState")
fun setButtonState(button: AppCompatButton, progress: Int) {
    button.apply {
        val color =
            if (progress > 0) {
                R.color.design_default_color_background
            } else {
                R.color.grey
            }
        setBackgroundColor(ContextCompat.getColor(context, color))
        setText(if (progress > 0) R.string.accept else R.string.rejected)
    }
}

@BindingAdapter("set_background")
fun setBackground(layout: ConstraintLayout, count: Int) {
    layout.apply {
        background = if (count > 5) {
            ContextCompat.getDrawable(context, R.drawable.shape_primary)
        } else {
            ContextCompat.getDrawable(context, R.drawable.shape_grey)
        }
    }
}

@BindingAdapter("text_color")
fun setTextColor(textView: AppCompatTextView, count: Int){
    textView.apply {
        if(count > 5){
            setTextColor(ContextCompat.getColor(context, R.color.grey))
        } else {
            setTextColor(ContextCompat.getColor(context, R.color.purple_700))
        }
    }
}

@BindingAdapter("text_background")
fun setTextBackground(textView: AppCompatTextView, count: Int){
    textView.apply {
        if(count > 5){
            setBackgroundColor(ContextCompat.getColor(context, R.color.grey))
        } else {
            setBackgroundColor(ContextCompat.getColor(context, R.color.purple_700))
        }
    }
}

@BindingAdapter("load_url")
fun loadImage(view: ImageView, url: String?){
    view.load(url)
}
