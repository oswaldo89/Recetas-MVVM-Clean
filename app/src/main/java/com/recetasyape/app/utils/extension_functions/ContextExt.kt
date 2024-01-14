package com.recetasyape.app.utils.extension_functions

import android.content.Context
import android.content.Intent
import android.net.Uri

fun Context.openImageInViewer(imageUrl: String) {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.setDataAndType(Uri.parse(imageUrl), "image/*")
    startActivity(intent)
}