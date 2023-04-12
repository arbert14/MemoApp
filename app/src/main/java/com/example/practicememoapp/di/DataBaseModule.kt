package com.example.practicememoapp.di

import android.content.Context
import androidx.room.Room
import com.example.practicememoapp.data.db.MemoDataBase
import com.example.practicememoapp.data.db.UserDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Singleton
    @Provides
    fun provideUserDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context, UserDataBase::class.java,
            "userDB"
        ).fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideUserDao(userDataBase: UserDataBase) = userDataBase.userDao()

    @Singleton
    @Provides
    fun provideMemoDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context, MemoDataBase::class.java, "MemoDB"
        ).fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideMemoDao(memoDataBase: MemoDataBase) = memoDataBase.memoDao()

}