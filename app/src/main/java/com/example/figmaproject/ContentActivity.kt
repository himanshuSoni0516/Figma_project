package com.example.figmaproject

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.figmaproject.adapter.ContentItemAdapter
import com.example.figmaproject.databinding.ActivityContentBinding
import com.example.figmaproject.model.ContentViewModel

class ContentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityContentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.txtBack.setOnClickListener {
            startActivity(Intent(this, FeedActivity::class.java))
        }
        binding.bNi1.setOnClickListener {
            startActivity(Intent(this, MarketActivity::class.java))
        }
        val contentData = ArrayList<ContentViewModel>()
        for (i in 1..10) {
            contentData.add(ContentViewModel("","Title $i","Description $i","time $i"))
            binding.rvContent.adapter = ContentItemAdapter(contentData)
            binding.rvContent.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        }
    }
}