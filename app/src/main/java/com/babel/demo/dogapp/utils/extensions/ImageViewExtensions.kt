package com.babel.demo.dogapp.utils.extensions

import android.widget.ImageView
import coil.load

fun ImageView.loadImage(imageUrl: String) {
    this.load(imageUrl) {
        crossfade(true)
        scaleType = ImageView.ScaleType.CENTER_CROP
    }
}