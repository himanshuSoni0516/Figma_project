package com.example.figmaproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.figmaproject.databinding.ContentItemLayoutBinding
import com.example.figmaproject.databinding.TrendingItemLayoutBinding
import com.example.figmaproject.model.ContentViewModel

class ContentItemAdapter (private val mList: List<ContentViewModel>): RecyclerView.Adapter<ContentItemAdapter.ViewHolder>(){
    lateinit var binding: ContentItemLayoutBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentItemAdapter.ViewHolder {
        return ViewHolder(ContentItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ContentItemAdapter.ViewHolder, position: Int) {
        val ItemsViewModel = mList[position]
        holder.txtTitle.text = ItemsViewModel.title
    }

    override fun getItemCount(): Int = mList.size
    class ViewHolder(binding: ContentItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        val txtTitle = binding.txtTitle
    }
}