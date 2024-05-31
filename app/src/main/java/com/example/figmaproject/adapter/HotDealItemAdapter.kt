package com.example.figmaproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.figmaproject.R
import com.example.figmaproject.databinding.FeedItemLayoutBinding
import com.example.figmaproject.databinding.HotDealItemLayoutBinding
import com.example.figmaproject.model.FeedViewModel
import com.example.figmaproject.model.ProductViewModel

class HotDealItemAdapter(private val mList: List<ProductViewModel>) : RecyclerView.Adapter<HotDealItemAdapter.ViewHolder>() {
	// create new views
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder { 
		// inflates the card_view_design view 
		// that is used to hold list item

		return ViewHolder(HotDealItemLayoutBinding.bind(LayoutInflater.from(parent.context).inflate(R.layout.hot_deal_item_layout, parent, false)))

	} 

	// binds the list items to a view 
	override fun onBindViewHolder(holder: ViewHolder, position: Int) { 
		val ProductViewModel = mList[position]
		holder.txtTitle.text = ProductViewModel.title

	}

	// return the number of the items in the list
	override fun getItemCount(): Int {
		return mList.size
	}

	// Holds the views for adding it to image and text
	class ViewHolder(binding: HotDealItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
		val txtTitle = binding.txtTitle
		val imgProduct = binding.imageView
	}
}
