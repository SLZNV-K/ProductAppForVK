package com.example.productappforvk.utill

import androidx.recyclerview.widget.DiffUtil
import com.example.productappforvk.Product

object ProductDiffCallBack : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Product, newItem: Product) = oldItem == newItem
}