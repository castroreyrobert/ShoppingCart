package com.castrorr.shoppingcart.features.product

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.castrorr.shoppingcart.R
import com.castrorr.shoppingcart.data.Product
import com.castrorr.shoppingcart.databinding.ItemProductBinding

class ProductListAdapter(
        private val listener: ProductListAdapterListener
) : RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {

    private var productList: MutableList<Product> = arrayListOf()

    fun addAll(products: List<Product>) {
        productList = products.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun getItemCount() = productList.size

    inner class ViewHolder(private val binding: ItemProductBinding): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("Range")
        fun bind(product: Product) {
            binding.product = product
            binding.productImageBackgroundColor.setBackgroundColor(Color.parseColor(product.bgColor))
            setProductImage(product.name)
            binding.tvAdd.setOnClickListener {
                product.quantity++
                binding.product = product
                listener.onAddClicked(product)
            }
            binding.buttonPlus.setOnClickListener {
                product.quantity = product.quantity + 1
                binding.product = product
                listener.onAddClicked(product)
            }
            binding.buttonMinus.setOnClickListener {
                product.quantity = product.quantity - 1
                binding.product = product
                listener.onAddClicked(product)
            }
        }

        /** Note: Not a proper way to display image, It should be in the json file. Maybe include in the json field as an image Url **/
        private fun setProductImage(name: String) {
            binding.productImage.background = when  {
                name.contains("flash", ignoreCase = true) -> ContextCompat.getDrawable(binding.root.context, R.drawable.ic_p1)
                name.contains("jacket", ignoreCase = true) -> ContextCompat.getDrawable(binding.root.context, R.drawable.ic_p_2)
                name.contains("striped", ignoreCase = true) -> ContextCompat.getDrawable(binding.root.context, R.drawable.ic_p_4)
                name.contains("blazer", ignoreCase = true) ->ContextCompat.getDrawable(binding.root.context, R.drawable.ic_p_3)
                else -> ContextCompat.getDrawable(binding.root.context, R.drawable.ic_p_5)
            }
        }
    }

    interface ProductListAdapterListener {
        fun onAddClicked(product: Product)
    }
}