package com.example.foodsapp.ui.home


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.foodsapp.R
import com.example.foodsapp.core.adapter.AllFoodsAdapter
import com.example.foodsapp.core.base.BaseFragment
import com.example.foodsapp.databinding.PageHomeBinding
import com.example.foodsapp.ui.main.MainScreenDirections


class HomePage : BaseFragment(R.layout.page_home){
    val viewModel:HomePageViewModel by viewModels()
    private val binding by viewBinding(PageHomeBinding::bind)
    private val adapter=AllFoodsAdapter()
    override fun onCreate(view: View, savedInstanceState: Bundle?) {
        viewModel.getFoodList()
        setAdapter()
        observ()
        adapter.onItemClick={
            findNavController().navigate(MainScreenDirections.actionMainScreenToDetailScreen(it))
        }

    }

    private fun setAdapter() {
        binding.recycleAdapter.adapter=adapter
        binding.recycleAdapter.layoutManager = LinearLayoutManager(context)
    }

    private fun observ() {
       viewModel.successResponse.observe(viewLifecycleOwner){ it->
          adapter.addData(it!!.meals)

       }

        viewModel.errorDataLive.observe(viewLifecycleOwner){ data->
            Log.d("MYDATA","dataError : ${data!!}")
        }
    }

}