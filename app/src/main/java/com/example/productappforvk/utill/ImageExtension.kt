package com.example.productappforvk.utill

import android.widget.ImageView
import com.bumptech.glide.Glide


fun ImageView.load(url: String) =
    Glide.with(this)
        .load(url)
        .timeout(10_000)
        .into(this)