package com.castrorr.shoppingcart

import android.app.Activity
import android.app.Application
import com.castrorr.shoppingcart.di.AppComponent
import com.castrorr.shoppingcart.di.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class ShoppingCartApplication:  Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector() = dispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
        DaggerAppComponent
            .builder()
            .application(this)
            .build()
            .inject(this)
    }
}