package com.example.practicememoapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.practicememoapp.data.dao.UserDao
import com.example.practicememoapp.data.entites.User

// hilt 에서는 추상 클래스와 추상 메서드로 만들고 module 에서 구현한 후 실제 사용하는 곳에 inject 하여 사용
@Database(entities = [User::class], version = 1)
abstract class UserDataBase: RoomDatabase() {
    abstract fun userDao(): UserDao
}