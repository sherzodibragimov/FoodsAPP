package com.example.foodsapp.core.adapter

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.foodsapp.ui.favorite.FavouritePage
import com.example.foodsapp.ui.home.HomePage
import com.example.foodsapp.ui.settings.SettingsScreen


class MainAdapter(fragmentManager: FragmentManager,lifecycle: Lifecycle):FragmentStateAdapter(fragmentManager,lifecycle)
{
    override fun getItemCount(): Int=3

    override fun createFragment(position: Int) = when(position){
        0->HomePage()
        1->FavouritePage()
        2->SettingsScreen()
        else-> SettingsScreen()
    }
}