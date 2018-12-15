package ru.you11.repasttestproject.Utils

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.widget.ImageView

fun setCircularImage(imageView: ImageView, context: Context) {
    val imageBitmap = (imageView.drawable as BitmapDrawable).bitmap
    val imageDrawable = RoundedBitmapDrawableFactory.create(context.resources, imageBitmap)
    imageDrawable.isCircular = true
    imageDrawable.cornerRadius = Math.max(imageBitmap.width, imageBitmap.height) / 2.0f
    imageView.setImageDrawable(imageDrawable)
}