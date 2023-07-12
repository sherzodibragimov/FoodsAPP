package com.example.foodsapp.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.example.foodsapp.R
import com.example.foodsapp.core.base.BaseFragment
import com.example.foodsapp.databinding.ScreenDetailBinding


class DetailScreen : BaseFragment(R.layout.screen_detail) {
    val viewModel: DetailVIewModel by viewModels()
    private val binding by viewBinding ( ScreenDetailBinding::bind)
    val args:DetailScreenArgs by navArgs ()
    override fun onCreate(view: View, savedInstanceState: Bundle?) {
        viewModel.getFoods(args.id)
        observ()
        loadAction()
    }

    private fun loadAction() {
        binding.backButton.setOnClickListener {
            
        }
        binding.imageFood.setOnClickListener {

        }
    }

    private fun observ() {
        viewModel.successResponse.observe(viewLifecycleOwner){ it->
            binding.topNameFood.setText(it!!.meals[0].strMeal)
           binding.imageFood.load(it!!.meals[0].strMealThumb)
            binding.nameFood.setText(it!!.meals[0].strMeal)
            binding.instructions.setText(it!!.meals[0].strInstructions)

        }

        viewModel.errorDataLive.observe(viewLifecycleOwner){ data->

        }
    }

}