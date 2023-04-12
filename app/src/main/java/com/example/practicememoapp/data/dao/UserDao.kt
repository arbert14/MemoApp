package com.example.practicememoapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.practicememoapp.data.entites.User

@Dao
interface UserDao {

    // 유저 추가
    @Insert
    suspend fun insertUser(user: User)

    // 아이디 검색
    @Query("SELECT * FROM USER WHERE id = :id")
    suspend fun selectUser(id: String): User?

}