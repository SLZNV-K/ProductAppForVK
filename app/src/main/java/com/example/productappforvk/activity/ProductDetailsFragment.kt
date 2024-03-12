package com.example.productappforvk.activity

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.productappforvk.activity.ListProductsFragment.Companion.EXTRA_DESCRIPTION
import com.example.productappforvk.activity.ListProductsFragment.Companion.EXTRA_DISCOUNT
import com.example.productappforvk.activity.ListProductsFragment.Companion.EXTRA_IMAGE
import com.example.productappforvk.activity.ListProductsFragment.Companion.EXTRA_IMAGES_LIST
import com.example.productappforvk.activity.ListProductsFragment.Companion.EXTRA_PRICE
import com.example.productappforvk.activity.ListProductsFragment.Companion.EXTRA_RATING
import com.example.productappforvk.activity.ListProductsFragment.Companion.EXTRA_TITLE
import com.example.productappforvk.databinding.FragmentProductDetailsBinding
import com.example.productappforvk.utill.load


class ProductDetailsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentProductDetailsBinding.inflate(layoutInflater, container, false)

        val titleText = arguments?.getString(EXTRA_TITLE)
        val imagePreviewURL = arguments?.getString(EXTRA_IMAGE)!!
        val ratingText = arguments?.getString(EXTRA_RATING)
        val discountText = "-${arguments?.getString(EXTRA_DISCOUNT)}%"
        val descriptionText = arguments?.getString(EXTRA_DESCRIPTION)
        val priceText = "${arguments?.getString(EXTRA_PRICE)} руб"
        val images = arguments?.getStringArray(EXTRA_IMAGES_LIST)!!.toMutableList()
        images.add(0, imagePreviewURL)


        with(binding) {
            rating.text = ratingText
            discountPercentage.text = discountText
            price.text = priceText
            description.text = descriptionText
            title.text = titleText
            addImagesToLinearLayout(container!!.context, images, layoutImages)
        }
        return binding.root
    }

    private fun addImagesToLinearLayout(
        context: Context,
        imagesUrls: List<String>,
        linearLayout: LinearLayout
    ) {
        for (imageUrl in imagesUrls) {
            val cardView = CardView(context)
            val imageView = ImageView(context)
            val padding = 20
            imageView.load(imageUrl)
            cardView.addView(imageView)
            cardView.radius = padding.toFloat()

            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.setMargins(padding, padding, padding, padding)
            cardView.layoutParams = layoutParams
            linearLayout.addView(cardView)
        }
    }
}