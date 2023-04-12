package com.example.practicememoapp.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.example.practicememoapp.R
import com.example.practicememoapp.data.db.UserDataBase
import com.example.practicememoapp.data.entites.User
import com.example.practicememoapp.databinding.FragmentLoginBinding
import com.example.practicememoapp.ui.MainActivity
import com.example.practicememoapp.ui.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject
import kotlin.math.log

@AndroidEntryPoint
class LoginFragment: Fragment(), MainActivity.OnBackPressedListener {
    private var _binding: FragmentLoginBinding? =null
    private val binding: FragmentLoginBinding
        get() = _binding!!

     private val loginViewModel: LoginViewModel by viewModels<LoginViewModel>()
    private val activity by lazy {
        requireActivity() as MainActivity
    }
    override fun onBackPressed() {
        requireActivity().finish()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(layoutInflater)
        binding.loginFragment = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun onClickedLoginBtn() {
        lifecycleScope.launch {
            val userId = binding.idEt.text.toString().trim()
            val userPw = binding.pwEt.text.toString().trim()

            // 로그인 정보 저장
            loginViewModel.saveUserLoginInfo(userId)

            // 아이디가 있으면 메모 조회 페이지로 바로 이동
            if(loginViewModel.selectUser(userId) != null) {
                activity.onReplaceFragment(ShowMemoFragment(), false)
            } else {
                // 아이디가 없으면 room DB 에 추가 후 메모 조회 페이지로 이동
                // 가입(insert) 후 로그인 진행
                withContext(Dispatchers.IO){
                    loginViewModel.insertUser(
                        User(0, userId, userPw)
                    )
                }

                activity.onReplaceFragment(ShowMemoFragment(), false)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}