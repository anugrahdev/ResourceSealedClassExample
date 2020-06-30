package com.anugrahdev.resourcesealedclassexample.utils

import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import coil.api.load
import com.anugrahdev.resourcesealedclassexample.R

@BindingAdapter("setImage")
fun ImageView.setImage(imageUrl: String) {
    load(imageUrl) {
        crossfade(true)
    }
}