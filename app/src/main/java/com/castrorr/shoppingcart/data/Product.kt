package com.castrorr.shoppingcart.data

data class Product(
    val id: String = "",
    val name: String = "",
    val category: String = "",
    val price: Double = 0.0,
    val bgColor: String = "",
    var quantity: Int = 0
)
