package com.example.foodsapp.ui.bottomsheet

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.foodsapp.R
import com.example.foodsapp.core.adapter.CategoryAdapter
import com.example.foodsapp.core.util.Constants

import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.greenrobot.eventbus.EventBus

class HomeBottomSheet:BottomSheetDialogFragment() {
        private val viewModel:BottomSheetViewModel by viewModels()
        private val adapter=CategoryAdapter()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel.getCategory()
        return inflater.inflate(R.layout.bottom_sheet_layout,container,false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView=view.findViewById<RecyclerView>(R.id.category_recycle)
        val searchButton=view.findViewById<Button>(R.id.bottom_sheet_button)
        recyclerView.adapter=adapter
        observer()
        adapter.onItemSelect={data->
            Constants.selectID=data
            observer()
            Log.d("asdasd","${Constants.selectID}")
        }
        searchButton.setOnClickListener {
            EventBus.getDefault().post(Constants.selectID)
            dismiss()
        }
    }




    private fun observer() {
        viewModel.successResponse.observe(viewLifecycleOwner){
            adapter.addData(it!!.meals)
        }
        viewModel.errorDataLive.observe(viewLifecycleOwner){
            Toast.makeText(view?.context, "INTERNET CONNECTION ERROR", Toast.LENGTH_SHORT).show()
        }
    }
}