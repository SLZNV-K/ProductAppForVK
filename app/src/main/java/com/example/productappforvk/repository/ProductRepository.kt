package com.example.productappforvk.repository

import androidx.paging.PagingData
import com.example.productappforvk.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    val data: Flow<PagingData<Product>>
}