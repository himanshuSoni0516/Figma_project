package com.example.figmaproject

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.figmaproject.databinding.ActivityRadiosBinding

class RadiosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRadiosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRadiosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.txtBack.setOnClickListener {
            startActivity(Intent(this, ProfilePhotosActivity::class.java))
        }
        binding.bNi1.setOnClickListener {
            startActivity(Intent(this, ImagesActivity::class.java))
        }
    }
}