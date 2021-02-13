package com.castrorr.shoppingcart.di.builder

import com.castrorr.shoppingcart.features.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity

}