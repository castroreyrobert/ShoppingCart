package com.castrorr.shoppingcart.di.module

import android.content.Context
import com.castrorr.shoppingcart.ShoppingCartApplication
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Singleton
    @Binds
    abstract fun providesApplicationContext(app: ShoppingCartApplication): Context
}