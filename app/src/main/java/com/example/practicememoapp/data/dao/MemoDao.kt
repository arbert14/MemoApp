package com.example.practicememoapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.practicememoapp.data.entites.Memo

@Dao
interface MemoDao {

    @Insert
    suspend fun insertMemo(memo: Memo)

    @Query("SELECT * FROM MEMO WHERE id = :id ORDER BY idx asc")
    suspend fun selectMemo(id: String): List<Memo>

    @Update
    suspend fun updateMemo(memo: Memo)

}