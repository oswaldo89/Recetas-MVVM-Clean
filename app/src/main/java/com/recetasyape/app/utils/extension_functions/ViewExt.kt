package com.recetasyape.app.utils.extension_functions

import android.view.View
import com.recetasyape.app.utils.SafeClickListener

fun View.setOnSafeClickListener(onSafeClick: (View) -> Unit) {
    setOnClickListener(SafeClickListener { v ->
        onSafeClick(v)
    })
}

fun View.setVisibleOrGone(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}