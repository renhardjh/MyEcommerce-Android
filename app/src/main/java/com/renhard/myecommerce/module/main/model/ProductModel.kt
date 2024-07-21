package com.renhard.myecommerce.module.main.model

import java.io.Serializable

class ProductModel(
    val id: Int,
    val name: String,
    val price: Double,
    val qty: Int,
    val description: String,
    val image: Int
): Serializable
