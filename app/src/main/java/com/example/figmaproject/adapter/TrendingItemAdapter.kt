package com.example.figmaproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.figmaproject.databinding.HotDealItemLayoutBinding
import com.example.figmaproject.databinding.TrendingItemLayoutBinding
import com.example.figmaproject.model.ProductViewModel

class TrendingItemAdapter(private var mList: List<ProductViewModel>): RecyclerView.Adapter<TrendingItemAdapter.ViewHolder>(){
    lateinit var binding: TrendingItemLayoutBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TrendingItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TrendingItemAdapter.ViewHolder, position: Int) {
        val product = mList[position]
        holder.productTitle.text = product.title
    }

    override fun getItemCount(): Int = mList.size

    class ViewHolder(binding: TrendingItemLayoutBinding): RecyclerView.ViewHolder(binding.root){
        val productTitle = binding.txtTitle
    }
}