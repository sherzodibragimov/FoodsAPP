package com.example.foodsapp.ui.favorite

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.foodsapp.R
import com.example.foodsapp.core.adapter.FavouriteAdapter
import com.example.foodsapp.core.base.BaseFragment
import com.example.foodsapp.core.db.SaveFoods
import com.example.foodsapp.core.model.allfood.Meal
import com.example.foodsapp.core.util.gone
import com.example.foodsapp.databinding.PageFavouriteBinding
import com.example.foodsapp.ui.main.MainScreenDirections


class FavouritePage :BaseFragment(R.layout.page_favourite){
    private val viewModel:FavouriteViewModel by viewModels()
    private val binding by viewBinding(PageFavouriteBinding::bind)
    private val adapter= FavouriteAdapter()
    private val arrayList=ArrayList<Meal>()
    override fun onCreate(view: View, savedInstanceState: Bundle?) {
        setAdapter()
        viewModel.getFoodList()
        observ()
        adapter.onItemClick={
            findNavController().navigate(MainScreenDirections.actionMainScreenToDetailScreen(it))
        }
    }

    override fun onResume() {
        super.onResume()
        observ()
    }

    private fun observ() {
        viewModel.successResponse.observe(viewLifecycleOwner){
            for (i in 0 until it!!.meals.size){
                if (SaveFoods.getSaveResult().contains(it.meals[i].idMeal.toInt())){
                    arrayList.add(it.meals[i])
                }
            }
            binding.shimmer.gone()
            adapter.addData(arrayList)
            arrayList.clear()
        }
        viewModel.errorDataLive.observe(viewLifecycleOwner){
            Toast.makeText(context, "${it.toString()}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setAdapter() {
        binding.favouriteRecycle.adapter=adapter
        binding.favouriteRecycle.layoutManager=LinearLayoutManager(context)
    }

}