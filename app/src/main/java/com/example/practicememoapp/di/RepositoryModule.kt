package com.example.practicememoapp.di

import com.example.practicememoapp.data.repository.UserRepository
import com.example.practicememoapp.data.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideUserRepository(userDao: UserDao) = UserRepository(userDao)

}