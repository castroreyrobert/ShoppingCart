package com.castrorr.shoppingcart.features.order

import android.os.Bundle
import android.os.FileUtils
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.castrorr.shoppingcart.R
import com.castrorr.shoppingcart.data.ProductsHelper

class OrderConfirmationFragment: Fragment(R.layout.fragment_order_confirmation) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonReturnToProduct = view.findViewById<View>(R.id.button_return_to_products)
        val orderIdTextView = view.findViewById<TextView>(R.id.tv_order_id)
        val orderId = System.currentTimeMillis()
        ProductsHelper.saveCartToJsonFile(requireContext(), ProductsHelper.cart.value ?: emptyList())
        orderIdTextView.text = String.format(getString(R.string.your_order_id_title), orderId)
        buttonReturnToProduct.setOnClickListener {
            findNavController().navigate(R.id.action_nav_order_confirmation_to_nav_product_list)
        }
    }
}