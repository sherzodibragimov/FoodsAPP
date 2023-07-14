package com.example.foodsapp.core.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodsapp.R
import com.example.foodsapp.core.model.categoriy.Meal
import com.example.foodsapp.core.util.Constants
import com.example.foodsapp.databinding.ItemCategoryButtonBinding


class CategoryAdapter:RecyclerView.Adapter<CategoryAdapter.CategoryAdapterViewHolder>() {

    lateinit var onItemClick:((a:String)->Unit)
    private val data= ArrayList<Meal>()
    lateinit var onItemSelect:((a:String)->Unit)

    @SuppressLint("NotifyDataSetChanged")
    fun addData(date:List<Meal>){
        data.clear()
        this.data.addAll(date)
        notifyDataSetChanged()
    }
    inner class CategoryAdapterViewHolder(val binding: ItemCategoryButtonBinding):RecyclerView.ViewHolder(binding.root){
        @SuppressLint("ResourceAsColor")
        fun setdata(data:Meal) {
            binding.buttonCategory.setText(data.strCategory)
            if (data.strCategory==Constants.selectID){
                binding.buttonCategory.setBackgroundColor(Color.CYAN)
            }
            else{
                binding.buttonCategory.setBackgroundColor(R.color.shimmerColor)
            }
            binding.buttonCategory.setOnClickListener {
                onItemSelect.invoke(binding.buttonCategory.text.toString())
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapterViewHolder {
        return CategoryAdapterViewHolder(ItemCategoryButtonBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: CategoryAdapterViewHolder, position: Int) {
        val data = data[position]
            holder.setdata(data)
    }
}