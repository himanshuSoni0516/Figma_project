package com.example.figmaproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.figmaproject.databinding.HotDealItemLayoutBinding
import com.example.figmaproject.model.ProductViewModel

class HotDealItemAdapter(private var mList: List<ProductViewModel>): RecyclerView.Adapter<HotDealItemAdapter.ViewHolder>(){
    lateinit var binding: HotDealItemLayoutBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(HotDealItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: HotDealItemAdapter.ViewHolder, position: Int) {
        val product = mList[position]
        holder.productTitle.text = product.title
    }

    override fun getItemCount(): Int = mList.size

    class ViewHolder(binding: HotDealItemLayoutBinding): RecyclerView.ViewHolder(binding.root){
        val productTitle = binding.txtTitle
    }
}