package com.recetasyape.app.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.Toast
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.recetasyape.app.BuildConfig
import com.recetasyape.app.modules.home.domain.model.Recipe
import java.io.File
import java.io.FileOutputStream

class WhatsAppShareUtil {

    companion object {

        fun shareRecipeOnWhatsApp(context: Context, recipe: Recipe) {
            Glide.with(context).asBitmap().load(recipe.imageUrl).into(object : CustomTarget<Bitmap>() {

                override fun onLoadCleared(placeholder: Drawable?) {}

                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    val cachePath = File(context.cacheDir, "images")
                    cachePath.mkdirs() // don't forget to make the directory
                    val stream = FileOutputStream("$cachePath/image.png") // overwrites this image every time
                    resource.compress(Bitmap.CompressFormat.PNG, 100, stream)
                    stream.close()

                    val imagePath = File(context.cacheDir, "images")
                    val newFile = File(imagePath, "image.png")
                    val contentUri: Uri = FileProvider.getUriForFile(context, "${BuildConfig.APPLICATION_ID}.provider", newFile)

                    val intent = Intent(Intent.ACTION_SEND)
                    intent.type = "image/*"

                    val descriptionWithIngredients = buildDescriptionWithIngredients(recipe)
                    intent.putExtra(Intent.EXTRA_TEXT, descriptionWithIngredients)
                    intent.putExtra(Intent.EXTRA_STREAM, contentUri)

                    try {
                        context.startActivity(Intent.createChooser(intent, "Choose..."))
                    } catch (ex: ActivityNotFoundException) {
                        Toast.makeText(context, "WhatsApp has not been installed.", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }

        private fun buildDescriptionWithIngredients(recipe: Recipe): String {
            val stringBuilder = StringBuilder()
            stringBuilder.append("**${recipe.title}**\n\n")
            stringBuilder.append("${recipe.description}\n\n")

            stringBuilder.append("**Ingredientes:**\n")
            recipe.ingredients.forEach { ingredient ->
                stringBuilder.append("- $ingredient\n")
            }

            return stringBuilder.toString()
        }

    }
}