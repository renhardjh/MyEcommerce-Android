package com.renhard.myecommerce.module.main.viewmodel

import android.content.Context
import com.renhard.myecommerce.R
import com.renhard.myecommerce.module.main.model.ProductModel

class MainViewModel {
    lateinit var productData: List<ProductModel>

    fun getData(context: Context) {
        val data = mutableListOf<ProductModel>()
        val productNames = context.resources.getStringArray(R.array.product_names)
        val productPrices = context.resources.getIntArray(R.array.product_prices)
        val productImages = context.resources.obtainTypedArray(R.array.product_images)
        val productdescs = context.resources.getStringArray(R.array.product_descriptions)
        productNames.forEachIndexed { id, name ->
            val product = ProductModel(
                id + 1, name,
                productPrices[id].toDouble(), 1,
                productdescs[id],
                productImages.getResourceId(id, 0)
            )
            data.add(product)
        }
        productData = data
    }
}