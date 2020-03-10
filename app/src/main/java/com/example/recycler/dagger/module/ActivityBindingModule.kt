package com.example.recycler.dagger.module

import com.example.recycler.ui.MainActivity
import com.example.recycler.dagger.ViewModelBuilder
import com.example.recycler.ui.SearchFragment
import com.example.recycler.ui.ArticleListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
    abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
    abstract fun searchFragment() : SearchFragment

    @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
    abstract fun articleListFragment() : ArticleListFragment

}