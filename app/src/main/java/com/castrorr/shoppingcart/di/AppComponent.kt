package com.castrorr.shoppingcart.di

import android.app.Application
import com.castrorr.shoppingcart.di.builder.ActivityBuilder
import com.castrorr.shoppingcart.di.builder.FragmentBuilder
import com.castrorr.shoppingcart.ShoppingCartApplication
import com.castrorr.shoppingcart.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ViewModelModule::class,
        ActivityBuilder::class,
        FragmentBuilder::class,
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: ShoppingCartApplication)
}
