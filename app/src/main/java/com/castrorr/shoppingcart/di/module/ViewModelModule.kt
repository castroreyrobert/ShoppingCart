package com.castrorr.shoppingcart.di.module

import androidx.lifecycle.ViewModelProvider
import com.castrorr.shoppingcart.di.ViewModelFactory
import com.castrorr.shoppingcart.di.scope.ViewModelKey
import com.castrorr.shoppingcart.features.main.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(androidx.lifecycle.ViewModel::class)
    abstract fun bindCharityViewModel(viewModel: ViewModel): ViewModel

}
