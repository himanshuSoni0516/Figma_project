package com.example.figmaproject

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.figmaproject.adapter.FeedItemAdapter
import com.example.figmaproject.databinding.ActivityFeedBinding
import com.example.figmaproject.model.FeedViewModel

class FeedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFeedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFeedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.txtBack.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        binding.bNi1.setOnClickListener {
            startActivity(Intent(this, ContentActivity::class.java))
        }


        // ArrayList of class ItemsViewModel
        val data = ArrayList<FeedViewModel>()

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 1..20) {
            data.add(FeedViewModel("", "Item $i", "Description $i"))
        }

        // This will pass the ArrayList to our Adapter
        val adapter = FeedItemAdapter(data)

        // Setting the Adapter with the recyclerview
        binding.recyclerview.adapter = adapter
    }
}