package com.example.practicememoapp.di

import com.example.practicememoapp.ui.MemoAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AdapterModule {

    @Singleton
    @Provides
    fun provideMemoAdapter() = MemoAdapter()

}