package com.castrorr.shoppingcart.data

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.castrorr.shoppingcart.features.common.utils.FileUtils
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.IOException
import java.nio.charset.StandardCharsets

object ProductsHelper {

    var cart = MutableLiveData<ArrayList<Product>>()

    fun getAllProducts(context: Context): ProductsJson? {
        return try {
            val inputStream = context.assets.open("offline/products.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            val json = String(buffer, StandardCharsets.UTF_8)
            val gson: Gson = GsonBuilder().setLenient().create()
            gson.fromJson(json, object : TypeToken<ProductsJson?>() {}.type)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
    }

    fun saveCartToJsonFile(context: Context, products: List<Product>) {
        val gson = Gson()
        val string = gson.toJson(products)
        val buffer = string.byteInputStream()
        val file: File =  FileUtils.createFile(context, "orderid_${System.currentTimeMillis()}", "json")
        file.let { FileUtils.copyStreamToFile(buffer, file) }
    }

    fun addToCart(product: Product) {
        val cartList = cart.value ?: arrayListOf()
        if (product.quantity > 0)
         cartList.find { it.id == product.id }?.let { it.quantity = product.quantity } ?: cartList.add(product)
        else cartList.removeIf{ it.quantity <= 0}
        cart.value = cartList
    }

    fun removeToCart(product: Product) {
        val cartList = cart.value ?: arrayListOf()
        cartList.remove(product)
        cart.value = cartList
    }
}
