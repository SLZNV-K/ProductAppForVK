package com.example.productappforvk.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.productappforvk.adapter.ProductsAdapter
import com.example.productappforvk.databinding.ActivityMainBinding
import com.example.productappforvk.viewModel.ProductViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ProductViewModel()

        val adapter = ProductsAdapter()
        binding.recyclerViewProducts.adapter = adapter

        lifecycleScope.launch {
            viewModel.products.collectLatest {
                adapter.submitData(it)
            }
        }
    }
}