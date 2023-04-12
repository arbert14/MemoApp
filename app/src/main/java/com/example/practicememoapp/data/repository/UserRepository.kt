package com.example.practicememoapp.data.repository

import com.example.practicememoapp.data.dao.UserDao
import com.example.practicememoapp.data.entites.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDao: UserDao) {

    suspend fun selectUser(id: String): User? {
        return withContext(Dispatchers.IO){
            userDao.selectUser(id)
        }
    }

    suspend fun insertUser(user: User) {
        return withContext(Dispatchers.IO){
            userDao.insertUser(user)
        }
    }
}