package com.example.foodsapp.ui.settings

import android.os.Bundle

import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.foodsapp.R
import com.example.foodsapp.core.base.BaseFragment
import com.example.foodsapp.core.db.ThemeCache
import com.example.foodsapp.core.util.Constants
import com.example.foodsapp.databinding.ScreenSettingsBinding


class SettingsScreen : BaseFragment(R.layout.screen_settings) {
    private val binding by viewBinding(ScreenSettingsBinding::bind)
    override fun onCreate(view: View, savedInstanceState: Bundle?) {
        if (ThemeCache.getIndex()==0){
            binding.switchButton.setIsNight(false)
        }
        else{
            binding.switchButton.setIsNight(true)
        }
        binding.switchButton.setListener {
            Constants.ID=0
            if (ThemeCache.getIndex()==0){
                ThemeCache.changeNight()
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }else{
                ThemeCache.changeDay()
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

}