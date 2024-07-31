package com.example.figmaproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.figmaproject.databinding.ActivityAddFeedBinding
import com.example.figmaproject.databinding.ActivityFeedBinding
import com.google.firebase.firestore.FirebaseFirestore
import java.util.regex.Pattern

class AddFeedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddFeedBinding
    private lateinit var db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddFeedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = FirebaseFirestore.getInstance()
        binding.btnSubmit.setOnClickListener {
            val feedTitle = binding.editTitle.text.toString()
            val feedDescription = binding.editDetail.text.toString()


            if(feedTitle.isEmpty()){
                binding.editTitle.error = "Please enter title"
                return@setOnClickListener
            }

            if(feedDescription.isEmpty()){
                binding.editDetail.error = "Please enter detail"
                return@setOnClickListener
            }
            // Create a user data map to store in Firestore
            val feedData = hashMapOf<String, Any>(
                "title" to feedTitle,
                "decription" to feedDescription,
                "createdAt" to System.currentTimeMillis(),

            )
            createFeed(feedData)
        }
    }


    private fun createFeed(feed: HashMap<String, Any>) {
        setInProgress(true)
        db.collection("feeds")
            .document().set(feed)
            .addOnSuccessListener {
                setInProgress(false)
                Toast.makeText(this, "Feed added successfully", Toast.LENGTH_SHORT).show()
                finish()
                setInProgress(false)
            }
            .addOnFailureListener { e ->
                setInProgress(false)
                Log.w(TAG, "Error adding document", e)
            }
    }

    private fun setInProgress(inProgress: Boolean) {
        if (inProgress) {
            binding.progressBar.visibility = View.VISIBLE
            binding.btnSubmit.isEnabled = false
            binding.btnSubmit.alpha = .5f
        } else {
            binding.progressBar.visibility = View.GONE
            binding.btnSubmit.isEnabled = true
            binding.btnSubmit.alpha = 1f
        }
    }

    companion object {
        private const val TAG = "AddFeedActivity"
    }
}