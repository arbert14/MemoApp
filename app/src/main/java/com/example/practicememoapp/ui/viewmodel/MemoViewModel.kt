package com.example.practicememoapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.practicememoapp.data.entites.Memo
import com.example.practicememoapp.data.model.LoginModel
import com.example.practicememoapp.data.model.MemoModel
import com.example.practicememoapp.data.repository.MemoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MemoViewModel @Inject constructor(private val memoRepository: MemoRepository): ViewModel() {

    suspend fun selectMemoList(): List<Memo> {
        return withContext(Dispatchers.IO) {
            memoRepository.selectMemoList(LoginModel.userId)
        }
    }

    suspend fun insertMemo(memo: Memo) {
        if(memo.memoTitle != "" && memo.memoContent != "") {
            withContext(Dispatchers.IO) {
                memoRepository.insertMemo(memo)
            }
        }
    }

    suspend fun updateMemo(memo: Memo) {
        if(memo.memoTitle != "" && memo.memoContent != "") {
            withContext(Dispatchers.IO) {
                memoRepository.updateMemo(memo)
            }
        }
    }

    fun setEditMemo(memo: Memo) {
        MemoModel.editMemo = memo
    }

    fun getEditMemo(): Memo {
        return MemoModel.editMemo
    }
}