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
import com.example.foodsapp.core.util.gone
import com.example.foodsapp.databinding.PageHomeBinding
import com.example.foodsapp.ui.bottomsheet.HomeBottomSheet
import com.example.foodsapp.ui.main.MainScreenDirections
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class HomePage : BaseFragment(R.layout.page_home){
    val viewModel:HomePageViewModel by viewModels()
    private val binding by viewBinding(PageHomeBinding::bind)
    private val adapter=AllFoodsAdapter()
    private var homeBottomSheet:HomeBottomSheet?=null
    override fun onCreate(view: View, savedInstanceState: Bundle?) {
        homeBottomSheet= HomeBottomSheet()
        viewModel.getFoodList()
        setAdapter()
        observ()
        adapter.onItemClick={
            findNavController().navigate(MainScreenDirections.actionMainScreenToDetailScreen(it))
        }
        sheetView()

    }


    private fun sheetView() {
        binding.floatBtn.setOnClickListener {
            homeBottomSheet?.show(childFragmentManager,"My SHEET")
        }
    }

    private fun setAdapter() {
        binding.recycleAdapter.adapter=adapter
        binding.recycleAdapter.layoutManager = LinearLayoutManager(context)
    }

    private fun observ() {
       viewModel.successResponse.observe(viewLifecycleOwner){ it->
           binding.shimmer.gone()
          adapter.addData(it!!.meals)

       }

        viewModel.errorDataLive.observe(viewLifecycleOwner){ data->
            Log.d("MYDATA","dataError : ${data!!}")
        }
    }
    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }
@Subscribe(threadMode = ThreadMode.MAIN)
fun getCategoryEvent(str:String){
    viewModel.getFoodSearchList(str)
    observ()
}
}