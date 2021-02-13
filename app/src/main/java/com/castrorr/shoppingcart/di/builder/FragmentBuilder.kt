package com.castrorr.shoppingcart.di.builder

import com.castrorr.shoppingcart.features.cart.CartFragment
import com.castrorr.shoppingcart.features.checkout.CheckoutFragment
import com.castrorr.shoppingcart.features.order.OrderConfirmationFragment
import com.castrorr.shoppingcart.features.product.ProductListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector
    abstract fun productListFragment(): ProductListFragment

    @ContributesAndroidInjector
    abstract fun cartFragment(): CartFragment

    @ContributesAndroidInjector
    abstract fun checkoutFragment(): CheckoutFragment

    @ContributesAndroidInjector
    abstract fun orderConfirmationFragment(): OrderConfirmationFragment

}