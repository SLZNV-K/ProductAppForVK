package com.example.productappforvk

data class ProductResponse(
    val products: List<Product>,
    val total: Int,
    val skip: Int,
    val limit: Int
)

data class Product(
    val price: Int = 0,
    val discountPercentage: Double = 0.0,
    val rating: Double = 0.0,
    val stock: Int = 0,
    val brand: String? = null,
    val category: String? = null,
    val images: List<String>? = null,
    val id: Int,
    val title: String,
    val description: String,
    val thumbnail: String,
)