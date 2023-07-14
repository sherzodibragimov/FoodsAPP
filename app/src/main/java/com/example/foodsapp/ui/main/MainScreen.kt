package com.example.foodsapp.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.foodsapp.R
import com.example.foodsapp.core.adapter.MainAdapter
import com.example.foodsapp.core.base.BaseFragment
import com.example.foodsapp.core.db.ThemeCache
import com.example.foodsapp.core.util.Constants
import com.example.foodsapp.databinding.ScreenMainBinding
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class MainScreen :BaseFragment(R.layout.screen_main)  {
    private val binding by viewBinding(ScreenMainBinding::bind)
    override fun onCreate(view: View, savedInstanceState: Bundle?) {
        if (ThemeCache.getIndex()==0){
            ThemeCache.changeNight()
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }else{
            ThemeCache.changeDay()
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        setAdapter()
    }

    override fun onResume() {
        super.onResume()
        binding.bottomMenu.setActiveItem(Constants.ID)
    }
    private fun setAdapter() {
        val adapter = MainAdapter(childFragmentManager,lifecycle)
        binding.viewPager.adapter=adapter
        binding.viewPager.isUserInputEnabled=false
        binding.bottomMenu.onItemSelected={
            Constants.ID=it
            when(it){
                0->binding.viewPager.setCurrentItem(0,false)
                1->binding.viewPager.setCurrentItem(1,false)
                2->binding.viewPager.setCurrentItem(2,false)
            }
        }
    }
}