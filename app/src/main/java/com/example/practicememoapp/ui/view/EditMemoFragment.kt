package com.example.practicememoapp.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.practicememoapp.R
import com.example.practicememoapp.data.entites.Memo
import com.example.practicememoapp.databinding.FragmentEditMemoBinding
import com.example.practicememoapp.databinding.FragmentInsertMemoBinding
import com.example.practicememoapp.ui.MainActivity
import com.example.practicememoapp.ui.viewmodel.LoginViewModel
import com.example.practicememoapp.ui.viewmodel.MemoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class EditMemoFragment : Fragment(), MainActivity.OnBackPressedListener {
    private var _binding: FragmentEditMemoBinding? = null
    private val binding: FragmentEditMemoBinding
        get() = _binding!!
    private val activity: MainActivity by lazy {
        requireActivity() as MainActivity
    }
    private val memoViewModel: MemoViewModel by viewModels()
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onBackPressed() {
        activity.onReplaceFragment(ShowMemoFragment(), false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = FragmentEditMemoBinding.inflate(layoutInflater)
        binding.editMemoFragment = this
        binding.memoViewModel = memoViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    // 메모 저장
    fun onClickedSaveBtn() {
        lifecycleScope.launch {
            // 저장이 끝나면 조회 화면으로 다시 이동
            withContext(Dispatchers.IO) {
                memoViewModel.updateMemo(
                    Memo(
                        memoViewModel.getEditMemo().idx,
                        loginViewModel.getLoginId(),
                        binding.titleEt.text.toString(),
                        binding.contentEt.text.toString()
                    )
                )
            }

            // 조회 화면으로 이동
            activity.onReplaceFragment(ShowMemoFragment(), false)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}