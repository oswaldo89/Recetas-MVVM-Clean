package com.recetasyape.app.utils.extension_functions

import android.view.View
import com.recetasyape.app.utils.SafeClickListener

fun View.setOnSafeClickListener(onSafeClick: (View) -> Unit) {
    setOnClickListener(SafeClickListener { v ->
        onSafeClick(v)
    })
}