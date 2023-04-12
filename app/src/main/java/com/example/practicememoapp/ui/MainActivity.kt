package com.example.practicememoapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.practicememoapp.R
import com.example.practicememoapp.databinding.ActivityMainBinding
import com.example.practicememoapp.ui.view.SplashFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    // 뒤로 가기 눌렀을 때 프라그먼트 전달 인터페이스
    interface OnBackPressedListener {
        fun onBackPressed()
    }

    override fun onBackPressed() {
        val fragmentList = supportFragmentManager.fragments
        for (fragment in fragmentList) {
            if (fragment is OnBackPressedListener) {
                (fragment as OnBackPressedListener).onBackPressed()
                return
            }
        }

        super.onBackPressed()
        Timber.e(supportFragmentManager.backStackEntryCount.toString())
    }

    fun onAddFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().let {
            it.add(R.id.nav_host_fragment, fragment, "SplashFragment")
            it.addToBackStack(null)
            it.commitAllowingStateLoss()
        }
    }

    fun onReplaceFragment(fragment: Fragment, addBackStack: Boolean = true) {
        supportFragmentManager.beginTransaction().let {
            it.replace(R.id.nav_host_fragment, fragment, "SplashFragment")
            if(addBackStack) {
                it.addToBackStack(null)
            }
            it.commitAllowingStateLoss()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpSplash()
    }

    private fun setUpSplash() {
        onReplaceFragment(SplashFragment(), false)
    }
}