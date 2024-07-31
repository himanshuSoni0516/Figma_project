package com.example.figmaproject

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.figmaproject.adapter.HotDealItemAdapter
import com.example.figmaproject.adapter.TrendingItemAdapter
import com.example.figmaproject.databinding.ActivityMarketBinding
import com.example.figmaproject.model.ProductViewModel

class MarketActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMarketBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMarketBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.txtBack.setOnClickListener {
            startActivity(Intent(this, ContentActivity::class.java))
        }
        binding.bNi1.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }
        binding.bNi2.setOnClickListener {
            startActivity(Intent(this, CongratulationsActivity::class.java))
        }
        val hotDealData = ArrayList<ProductViewModel>()
        for (i in 1..10) {
            hotDealData.add(ProductViewModel("","item $i","item $i"))
        }
        val adapter = HotDealItemAdapter(hotDealData)
        binding.rvHotDeals.adapter = adapter
        binding.rvHotDeals.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


        val TrendingData = ArrayList<ProductViewModel>()
        for (i in 1..5) {
            TrendingData.add(ProductViewModel("","item $i","item $i"))
        }
        val adapter2 = TrendingItemAdapter(TrendingData)
        binding.rvTrending.adapter = adapter2
        binding.rvTrending.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }
}