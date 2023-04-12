package com.example.practicememoapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.practicememoapp.data.dao.MemoDao
import com.example.practicememoapp.data.entites.Memo

@Database(entities = [Memo::class], version = 1)
abstract class MemoDataBase: RoomDatabase() {
    abstract fun memoDao(): MemoDao
}