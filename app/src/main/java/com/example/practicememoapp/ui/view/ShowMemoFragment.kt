package com.example.practicememoapp.ui.view

import android.icu.lang.UCharacter.VerticalOrientation
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.practicememoapp.R
import com.example.practicememoapp.data.entites.Memo
import com.example.practicememoapp.databinding.FragmentShowMemoBinding
import com.example.practicememoapp.ui.MemoAdapter
import com.example.practicememoapp.ui.viewmodel.LoginViewModel
import com.example.practicememoapp.ui.viewmodel.MemoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class ShowMemoFragment : Fragment() {
    @Inject lateinit var memoAdapter : MemoAdapter
    private val memoViewModel: MemoViewModel by viewModels()
    private val loginViewModel: LoginViewModel by viewModels()
    private var _binding: FragmentShowMemoBinding? = null
    private val binding: FragmentShowMemoBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShowMemoBinding.inflate(layoutInflater)
        binding.showMemoFragment = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpMemoRecyclerView()
        selectMemoList()
    }

    private fun setUpMemoRecyclerView() {
        binding.memoRv.apply {
            setHasFixedSize(true)
            this.adapter = memoAdapter
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(), RecyclerView.VERTICAL
                )
            )
        }
    }

    // 메모 조회
    private fun selectMemoList() {
        lifecycleScope.launch {
            Timber.e("memoViewModel.selectMemoList() ${memoViewModel.selectMemoList()}")
            memoAdapter.submitList(memoViewModel.selectMemoList())
        }
    }

    // 메모 추가
    fun insertMemo() {
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                memoViewModel.insertMemo(
                    Memo(
                        0,
                        loginViewModel.getLoginId(),
                        "memo",
                        "memo"
                    )
                )
            }

            selectMemoList()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}