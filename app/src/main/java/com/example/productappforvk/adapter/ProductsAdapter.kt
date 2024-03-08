package com.example.productappforvk.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.productappforvk.Product
import com.example.productappforvk.databinding.CardProductBinding
import com.example.productappforvk.utill.ProductDiffCallBack
import com.example.productappforvk.utill.load

class ProductsAdapter : PagingDataAdapter<Product, ProductViewHolder>(ProductDiffCallBack) {
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position) ?: return
        holder.bind(product)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = CardProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(view)
    }
}

class ProductViewHolder(private val binding: CardProductBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(product: Product) {
        with(binding) {
            title.text = product.title
            description.text = product.description
            thumbnail.clipToOutline = true
            thumbnail.load(product.thumbnail)
            val priceText = "${product.price} руб"
            price.text = priceText

        }
    }
}
