package com.example.foodsapp.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.foodsapp.R
import com.example.foodsapp.core.model.allfood.Meal
import com.example.foodsapp.databinding.ItemFoodsBinding

class AllFoodsAdapter:RecyclerView.Adapter<AllFoodsAdapter.AllFoodsAdapterAdapterViewHolder>() {

    lateinit var onItemClick:((a:Int)->Unit)
    private val data= ArrayList<Meal>()

    fun addData(date:List<Meal>){
        data.clear()
        this.data.addAll(date)
        notifyDataSetChanged()
    }
    inner class AllFoodsAdapterAdapterViewHolder(val binding: ItemFoodsBinding):RecyclerView.ViewHolder(binding.root){
        fun setdata(data:Meal) {
            binding.imageFood.load(data.strMealThumb){
                placeholder (R.drawable.unnamed)
            }
            binding.nameFood.setText(data.strMeal)
            binding.strArea.setText(data.strArea)
            binding.strCategory.setText(data.strCategory)
            binding.root.setOnClickListener {
                onItemClick.invoke(data.idMeal.toInt())
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllFoodsAdapterAdapterViewHolder {
        return AllFoodsAdapterAdapterViewHolder(ItemFoodsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: AllFoodsAdapterAdapterViewHolder, position: Int) {
        val data = data[position]
        holder.setdata(data)
    }
}