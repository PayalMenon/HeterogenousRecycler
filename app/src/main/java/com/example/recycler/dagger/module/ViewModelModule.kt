package com.example.recycler.dagger.module

import androidx.lifecycle.ViewModel
import com.example.recycler.dagger.ViewModelKey
import com.example.recycler.viewmodel.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindArticleSearchViewModel(viewModel: SearchViewModel): ViewModel
}