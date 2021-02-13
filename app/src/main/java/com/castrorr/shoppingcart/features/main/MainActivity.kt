package com.castrorr.shoppingcart.features.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.castrorr.shoppingcart.R
import com.castrorr.shoppingcart.data.Product
import com.castrorr.shoppingcart.data.ProductsHelper
import com.castrorr.shoppingcart.features.cart.CartFragment

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var countTextView: TextView
    private lateinit var navController: NavController

    private var cartList = emptyList<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        countTextView = findViewById(R.id.tv_cart_count)
        val cartButton = findViewById<ImageView>(R.id.image_view_cart)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        cartButton.setOnClickListener(this)
        countTextView.setOnClickListener(this)
        setUpCartObserver()
    }

    private fun setUpCartObserver() {
        ProductsHelper.cart.observe(this, {
            cartList = it
            countTextView.isVisible = it.count() > 0
            countTextView.text = it.size.toString()
        })
    }

    override fun onClick(v: View?) {
        if (cartList.isNotEmpty())
            navController.navigate(R.id.nav_cart)
        else Toast.makeText(this, getString(R.string.no_items_in_the_cart), Toast.LENGTH_LONG).show()
    }
}