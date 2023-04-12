package com.example.practicememoapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.practicememoapp.data.repository.UserRepository
import com.example.practicememoapp.data.entites.User
import com.example.practicememoapp.data.model.LoginModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val userRepository: UserRepository): ViewModel() {

    suspend fun selectUser(id: String): User? {
        return withContext(Dispatchers.IO) {
            userRepository.selectUser(id)
        }
    }

    suspend fun insertUser(user: User) {
        return withContext(Dispatchers.IO) {
            userRepository.insertUser(user)
        }
    }

    fun saveUserLoginInfo(userId: String) {
        LoginModel.userId = userId
    }

    fun getLoginId(): String = LoginModel.userId
}