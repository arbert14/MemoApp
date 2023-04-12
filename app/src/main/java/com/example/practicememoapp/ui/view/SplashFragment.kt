package com.example.practicememoapp.ui.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.practicememoapp.R
import com.example.practicememoapp.databinding.FragmentSplashBinding
import com.example.practicememoapp.ui.MainActivity
import timber.log.Timber

class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding: FragmentSplashBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 2초후에 로그인 화면 표시
        Handler(Looper.myLooper()!!).postDelayed({
            val activity = requireActivity() as MainActivity
            activity.onReplaceFragment(LoginFragment(), false)
        }, 2000)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}