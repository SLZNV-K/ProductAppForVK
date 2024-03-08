package com.example.productappforvk.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.productappforvk.Product
import com.example.productappforvk.repository.ProductRepositoryImpl
import kotlinx.coroutines.flow.Flow

class ProductViewModel : ViewModel() {

    private val repository = ProductRepositoryImpl()
    val products: Flow<PagingData<Product>> = repository.data.cachedIn(viewModelScope)

}