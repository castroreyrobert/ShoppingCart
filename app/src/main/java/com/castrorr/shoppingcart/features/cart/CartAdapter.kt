package com.castrorr.shoppingcart.features.cart

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.castrorr.shoppingcart.R
import com.castrorr.shoppingcart.data.Product
import com.castrorr.shoppingcart.databinding.ItemCartBinding

class CartAdapter(private val listener: CartAdapterListener): RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    private var cartList = mutableListOf<Product>()

    fun setCartList(carts: List<Product>) {
        cartList = carts.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(cartList[position], position)
    }

    override fun getItemCount() = cartList.size


    inner class ViewHolder(private val binding: ItemCartBinding): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n", "Range")
        fun bind(product: Product, position: Int) {
            binding.product = product
            binding.bgColor.setBackgroundColor(Color.parseColor(product.bgColor))
            binding.tvProductPrice.text = "$ ${product.price * product.quantity}"
            binding.buttonClose.setOnClickListener { listener.onItemRemoved(product, position) }
            binding.root.backgroundTintMode = android.graphics.PorterDuff.Mode.MULTIPLY
        }
    }

    interface CartAdapterListener {
        fun onItemRemoved(product: Product, position: Int)
    }
}