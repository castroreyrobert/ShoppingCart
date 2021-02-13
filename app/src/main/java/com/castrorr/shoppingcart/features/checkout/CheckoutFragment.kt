package com.castrorr.shoppingcart.features.checkout

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.castrorr.shoppingcart.R
import com.castrorr.shoppingcart.data.ProductsHelper

class CheckoutFragment: Fragment(R.layout.fragment_checkout) {

    private var isAgree = false

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageAgree = view.findViewById<ImageView>(R.id.image_agree)
        val buttonPay = view.findViewById<Button>(R.id.button_pay)
        val name = view.findViewById<EditText>(R.id.name)
        val emailAddress = view.findViewById<EditText>(R.id.email)
        imageAgree.setOnClickListener {
            isAgree = !isAgree
            imageAgree.setImageDrawable(if (isAgree)
                ContextCompat.getDrawable(requireContext(), R.drawable.ic_form_toggle_on)
            else ContextCompat.getDrawable(requireContext(), R.drawable.ic_form_toggle_off))
            buttonPay.isEnabled = name.text.toString().isNotEmpty() && emailAddress.text.toString().isNotEmpty() && isAgree
        }
        val totalPrice = ProductsHelper.cart.value?.sumByDouble { it.price * it.quantity } ?: 0.0
        buttonPay.text = "Pay $$totalPrice"
        buttonPay.setOnClickListener { findNavController().navigate(R.id.action_nav_checkout_to_nav_order_confirmation) }
        emailAddress.doOnTextChanged { _, _, _, count ->
            buttonPay.isEnabled = count > 0 && name.text.toString().isNotEmpty() && isAgree
        }
        name.doOnTextChanged { _, _, _, count ->
            buttonPay.isEnabled = count > 0 && emailAddress.text.toString().isNotEmpty() && isAgree
        }
    }
}