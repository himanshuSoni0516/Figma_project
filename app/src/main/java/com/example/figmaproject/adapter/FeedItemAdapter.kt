package com.example.figmaproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.figmaproject.R
import com.example.figmaproject.databinding.FeedItemLayoutBinding
import com.example.figmaproject.model.FeedViewModel

class FeedItemAdapter(private val mList: List<FeedViewModel>) : RecyclerView.Adapter<FeedItemAdapter.ViewHolder>() {
	lateinit var binding: FeedItemLayoutBinding
	// create new views 
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder { 
		// inflates the card_view_design view 
		// that is used to hold list item

		return ViewHolder(FeedItemLayoutBinding.bind(LayoutInflater.from(parent.context).inflate(R.layout.feed_item_layout, parent, false)))
	}

	// binds the list items to a view 
	override fun onBindViewHolder(holder: ViewHolder, position: Int) { 

		val itemsViewModel = mList[position]

		holder.txtTitle.text = itemsViewModel.title
		holder.txtDescription.text = itemsViewModel.decription
	} 

	// return the number of the items in the list 
	override fun getItemCount(): Int { 
		return mList.size 
	} 

	// Holds the views for adding it to image and text 
	class ViewHolder(binding: FeedItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
		val txtTitle = binding.txtTitle
		val txtDescription = binding.txtDescription
	} 
}
