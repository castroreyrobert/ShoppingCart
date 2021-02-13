package com.castrorr.shoppingcart.features.product

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.castrorr.shoppingcart.R
import com.castrorr.shoppingcart.data.Product
import com.castrorr.shoppingcart.data.ProductsHelper
import java.util.*

class ProductListFragment: Fragment(R.layout.fragment_product_list),
    ProductListAdapter.ProductListAdapterListener {

    private lateinit var layoutNewlyAdded: View
    private lateinit var closeNewlyAdded: View
    private lateinit var textNewlyAdded: TextView
    private lateinit var filterGroup: RadioGroup

    private val adapter by lazy { ProductListAdapter(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerViewProduct = view.findViewById<RecyclerView>(R.id.recycler_view_product)
        layoutNewlyAdded = view.findViewById(R.id.layout_newly_added_product)
        closeNewlyAdded = view.findViewById(R.id.button_close)
        textNewlyAdded = view.findViewById(R.id.tv_product_added)
        filterGroup = view.findViewById(R.id.radio_group_filter)
        val products = ProductsHelper.getAllProducts(requireContext())?.products ?: emptyList()
        ProductsHelper.cart.value?.forEach { cart ->
            products.find { it.id == cart.id } ?.let {
                it.quantity = cart.quantity
            }
        }
        closeNewlyAdded.setOnClickListener { layoutNewlyAdded.isVisible = false }
        recyclerViewProduct.apply {
            adapter = this@ProductListFragment.adapter
            layoutManager = LinearLayoutManager(context)
            this@ProductListFragment.adapter.addAll(products = products)
        }
        filterGroup.setOnCheckedChangeListener { _ , checkedId ->
            when (checkedId) {
                R.id.filter_all -> adapter.addAll(products)
                R.id.filter_blazers -> adapter.addAll(products.filter { it.category.contentEquals(getString(R.string.blazer)) })
                R.id.filter_jacket -> adapter.addAll(products.filter { it.category.contentEquals(getString(R.string.jacket)) })
                R.id.filter_tees -> adapter.addAll(products.filter { it.category.contentEquals(getString(R.string.tee)) })
            }
        }
    }

    override fun onAddClicked(product: Product) {
       layoutNewlyAdded.isVisible = product.quantity == 1
        ProductsHelper.addToCart(product)
        if (product.quantity <= 0 ) ProductsHelper.removeToCart(product)
        if (product.quantity == 1) {
            textNewlyAdded.text =
                    String.format(Locale.getDefault(), getString(R.string.newly_added), product.name)
        }
    }
}