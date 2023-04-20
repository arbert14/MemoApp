package com.example.practicememoapp.data.repository

import com.example.practicememoapp.data.dao.MemoDao
import com.example.practicememoapp.data.entites.Memo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MemoRepository @Inject constructor(private val memoDao: MemoDao) {
    suspend fun selectMemoList(id: String): List<Memo>{
        return withContext(Dispatchers.IO){
            memoDao.selectMemo(id)
        }
    }

    suspend fun insertMemo(memo: Memo) {
        return withContext(Dispatchers.IO){
            memoDao.insertMemo(memo)
        }
    }

    suspend fun updateMemo(memo: Memo) {
        return withContext(Dispatchers.IO){
            memoDao.updateMemo(memo)
        }
    }
}