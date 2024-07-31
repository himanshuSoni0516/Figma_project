package com.example.figmaproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.figmaproject.adapter.FeedItemAdapter
import com.example.figmaproject.databinding.ActivityFeedBinding
import com.example.figmaproject.model.FeedViewModel
import com.example.figmaproject.utils.PrefManager
import com.google.firebase.firestore.FirebaseFirestore

class FeedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFeedBinding
    private lateinit var db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFeedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = FirebaseFirestore.getInstance()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.txtBack.setOnClickListener {
            PrefManager(this).clear()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        binding.bNi1.setOnClickListener {
            startActivity(Intent(this, ContentActivity::class.java))
        }

        binding.fabAddFeed.setOnClickListener {
            startActivity(Intent(this, AddFeedActivity::class.java))
        }

    }

    override fun onResume() {
        super.onResume()
        getFeedData()
    }

    private fun getFeedData() {
        setInProgress(true)

        // Access the "feeds" collection
        db.collection("feeds")
            .get()
            .addOnSuccessListener { documents ->
                // Create a list to hold the FeedViewModel data
                val feedList = mutableListOf<FeedViewModel>()

                for (document in documents) {
                    val feed = document.toObject(FeedViewModel::class.java)
                    feedList.add(feed)
                }

                // This will pass the ArrayList to our Adapter
                val adapter = FeedItemAdapter(feedList)

                // Setting the Adapter with the recyclerview
                binding.recyclerview.adapter = adapter
                setInProgress(false)
            }
            .addOnFailureListener { e ->
                setInProgress(false)
                Log.w(TAG, "Error getting documents", e)
                Toast.makeText(this, "Failed to retrieve feeds", Toast.LENGTH_SHORT).show()
            }
    }

    private fun setInProgress(inProgress: Boolean) {
        if (inProgress) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    companion object {
        private const val TAG = "FeedsActivity"
    }
}