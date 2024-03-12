package com.example.productappforvk.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.productappforvk.Product
import com.example.productappforvk.R
import com.example.productappforvk.adapter.OnInteractionListener
import com.example.productappforvk.adapter.ProductsAdapter
import com.example.productappforvk.databinding.FragmentListProductsBinding
import com.example.productappforvk.viewModel.ProductViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ListProductsFragment : Fragment() {

    private val viewModel = ProductViewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentListProductsBinding.inflate(layoutInflater, container, false)

        val adapter = ProductsAdapter(object : OnInteractionListener {
            override fun onProduct(product: Product) {
                findNavController().navigate(
                    R.id.action_listProductsFragment_to_productDetailsFragment,
                    Bundle().apply {
                        putString(EXTRA_TITLE, product.title)
                        putString(EXTRA_IMAGE, product.thumbnail)
                        putString(EXTRA_RATING, product.rating.toString())
                        putString(EXTRA_DISCOUNT, product.discountPercentage.toString())
                        putString(EXTRA_DESCRIPTION, product.description)
                        putString(EXTRA_PRICE, product.price.toString())
                        putStringArray(EXTRA_IMAGES_LIST, product.images!!.toTypedArray())
                    })
            }
        })
        with(binding) {
            recyclerViewProducts.adapter = adapter

            lifecycleScope.launch {
                viewModel.products.collectLatest {
                    adapter.submitData(it)
                }
            }

            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    adapter.loadStateFlow.collectLatest { state ->
                        swipeRefresh.isRefreshing = state.refresh is LoadState.Loading

                        if (state.refresh is LoadState.Error) {
                            AlertDialog.Builder(requireActivity()).apply {
                                setTitle(getString(R.string.error_loading))
                                setMessage(getString(R.string.error_message))
                                setPositiveButton(getString(R.string.retry)) { _, _ ->
                                    adapter.refresh()
                                }
                                setNegativeButton(getString(R.string.cancel)) { _, _ -> }
                                setCancelable(true)
                            }.create().show()
                        }
                    }
                }
            }
            swipeRefresh.setOnRefreshListener(adapter::refresh)

            return root
        }
    }

    companion object {
        const val EXTRA_TITLE = "EXTRA_TITLE"
        const val EXTRA_IMAGE = "EXTRA_IMAGE"
        const val EXTRA_RATING = "EXTRA_RATING"
        const val EXTRA_DISCOUNT = "EXTRA_DISCOUNT"
        const val EXTRA_DESCRIPTION = "EXTRA_DESCRIPTION"
        const val EXTRA_PRICE = "EXTRA_PRICE"
        const val EXTRA_IMAGES_LIST = "EXTRA_IMAGES_LIST"

    }
}
