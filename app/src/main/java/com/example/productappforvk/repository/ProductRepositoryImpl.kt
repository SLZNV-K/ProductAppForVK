package com.example.productappforvk.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.productappforvk.api.RetrofitClient.apiService

class ProductRepositoryImpl : ProductRepository {

        override val data = Pager(
        config = PagingConfig(pageSize = 20, enablePlaceholders = false),
        pagingSourceFactory = { ProductPagingSource(apiService) }
    ).flow
}