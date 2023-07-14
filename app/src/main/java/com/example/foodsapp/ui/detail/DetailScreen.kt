package com.example.foodsapp.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.example.foodsapp.R
import com.example.foodsapp.core.base.BaseFragment
import com.example.foodsapp.core.db.SaveFoods
import com.example.foodsapp.databinding.ScreenDetailBinding


class DetailScreen : BaseFragment(R.layout.screen_detail) {
    val viewModel: DetailVIewModel by viewModels()
    private val binding by viewBinding ( ScreenDetailBinding::bind)
    private var check=false
    val args:DetailScreenArgs by navArgs ()
    override fun onCreate(view: View, savedInstanceState: Bundle?) {
        viewModel.getFoods(args.id)
        check=SaveFoods.checkIndex(args.id)
        observ()
        loadAction()
        loadView()

    }

    private fun loadView() {
        if (SaveFoods.checkIndex(args.id)){
            binding.favouriteButton.setImageResource(R.drawable.star_on)
        }else{
            binding.favouriteButton.setImageResource(R.drawable.staroff)
        }
    }

    private fun loadAction() {
        binding.backButton.setOnClickListener {
        findNavController().popBackStack()
        }
        binding.favouriteButton.setOnClickListener {
            if(check){
                check=false
                SaveFoods.deleteID(args.id)
                binding.favouriteButton.setImageResource(R.drawable.staroff)
            }else{
                check=true
                binding.favouriteButton.setImageResource(R.drawable.star_on)
                SaveFoods.saveResult(args.id)
            }

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