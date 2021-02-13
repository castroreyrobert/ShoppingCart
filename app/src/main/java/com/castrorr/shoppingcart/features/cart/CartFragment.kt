package com.castrorr.shoppingcart.features.cart

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.compose.animation.core.LinearEasing
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.castrorr.shoppingcart.R
import com.castrorr.shoppingcart.data.Product
import com.castrorr.shoppingcart.data.ProductsHelper

class CartFragment: Fragment(R.layout.fragment_cart), CartAdapter.CartAdapterListener {

    private val adapter by lazy { CartAdapter(this) }
    private lateinit var totalPriceTextView: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonBuyNow = view.findViewById<View>(R.id.button_buy_now)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_cart)
        recyclerView.apply {
            adapter = this@CartFragment.adapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        totalPriceTextView = view.findViewById(R.id.tv_total_price)
        buttonBuyNow.setOnClickListener { findNavController().navigate(R.id.action_nav_cart_to_nav_checkout) }
        setUpCartObserver()
    }

    @SuppressLint("SetTextI18n")
    private fun setUpCartObserver() {
        ProductsHelper.cart.observe(viewLifecycleOwner, { products ->
            if (products.isEmpty()) activity?.onBackPressed()
            adapter.setCartList(products)
            val totalPrice = products.sumOf { it.price * it.quantity }
            totalPriceTextView.text = "$ $totalPrice"
        })
    }

    override fun onItemRemoved(product: Product, position: Int) {
        ProductsHelper.removeToCart(product)
    }
}