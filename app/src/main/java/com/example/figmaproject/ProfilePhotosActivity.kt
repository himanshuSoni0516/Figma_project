package com.example.figmaproject

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.figmaproject.databinding.ActivityProfilePhotosBinding

class ProfilePhotosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfilePhotosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityProfilePhotosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.imgBtn.setOnClickListener {
            startActivity(Intent(this, ProfilePostActivity::class.java))
        }
        binding.bNi1.setOnClickListener {
            startActivity(Intent(this, RadiosActivity::class.java))
        }
        binding.bNi2.setOnClickListener {
            startActivity(Intent(this, MessageActivity::class.java))
        }
        binding.bNi3.setOnClickListener {
            startActivity(Intent(this, RatingActivity::class.java))
        }
        binding.bNi4.setOnClickListener {
            startActivity(Intent(this, DrawerActivity::class.java))
        }
        binding.bNi5.setOnClickListener {
            startActivity(Intent(this, ComposeActivity::class.java))
        }
    }
}