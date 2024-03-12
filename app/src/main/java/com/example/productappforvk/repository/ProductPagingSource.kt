package com.example.productappforvk.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.productappforvk.Product
import com.example.productappforvk.api.ApiService
import com.example.productappforvk.error.ApiError

class ProductPagingSource(private val apiService: ApiService) : PagingSource<Int, Product>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        return try {
            val position = params.key ?: 0
            val response = apiService.getProducts(skip = position, limit = params.loadSize)

            if (!response.isSuccessful) {
                throw ApiError(response.code(), response.message())
            }
            val body = response.body() ?: throw ApiError(
                response.code(),
                response.message(),
            )

            LoadResult.Page(
                data = body.products,
                prevKey = if (position == 0) null else position - params.loadSize,
                nextKey = if (body.products.isEmpty()) null else position + params.loadSize
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return null
    }
}