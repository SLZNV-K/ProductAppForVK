package com.example.productappforvk.utill

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.productappforvk.R

fun ImageView.load(url: String) =
    Glide.with(this)
        .load(url)
        .timeout(10_000)
        .placeholder(R.drawable.ic_image_place_holder)
        .centerCrop()
        .override(500, 700)
        .error(R.drawable.error)
        .into(this)