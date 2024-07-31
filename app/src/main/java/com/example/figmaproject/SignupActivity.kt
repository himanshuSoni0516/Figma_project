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
import com.example.figmaproject.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.util.regex.Pattern

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = FirebaseFirestore.getInstance()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.txtLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.btnSignUp.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            val confirmPassword = binding.confirmPasswordEditText.text.toString()

            if(!Pattern.matches(Patterns.EMAIL_ADDRESS.pattern(),email)) {
                binding.emailEditText.error = "Invalid email address"
                return@setOnClickListener

            }

            if(password.length<6){
                binding.passwordEditText.error = "Password must be at least 6 characters"
                return@setOnClickListener
            }

            if(!password.equals(confirmPassword)){
                binding.confirmPasswordEditText.error = "Password does not match"
                return@setOnClickListener
            }

            createAccountWithFirebase(email,password)
        }
    }

    private fun createAccountWithFirebase(email: String, password: String) {
        setInProgress(true)
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val user = Firebase.auth.currentUser
                user?.let {
                    val uid = user.uid
                    val email = user.email
                    val createdAt = user.metadata?.creationTimestamp
                    val name = binding.nameEditText.text.toString()
                    // Create a user data map to store in Firestore
                    val userData = hashMapOf<String, Any>(
                        "uid" to uid,
                        "email" to (email ?: ""),
                        "createdAt" to (createdAt ?: System.currentTimeMillis()),
                        "name" to name
                    )
                    createUserIntoDatabase(userData)
                }
            }

            .addOnFailureListener {
                setInProgress(false)
                Toast.makeText(applicationContext, "Failed to create account", Toast.LENGTH_SHORT)
                    .show()
            }
    }


    private fun setInProgress(inProgress: Boolean) {
        if (inProgress) {
            binding.progressBar.visibility = View.VISIBLE
            binding.btnSignUp.isEnabled = false
            binding.btnSignUp.alpha = .5f
        } else {
            binding.progressBar.visibility = View.GONE
            binding.btnSignUp.isEnabled = true
            binding.btnSignUp.alpha = 1f
        }
    }

    private fun createUserIntoDatabase(user: HashMap<String, Any>) {
        val uid = user["uid"] as? String ?: return // Safely cast to String or return if null
        db.collection("users").document(uid)
            .set(user)
            .addOnSuccessListener {
                setInProgress(false)
                Toast.makeText(this, "Account created successfully", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
            .addOnFailureListener { e ->
                setInProgress(false)
                Log.w(TAG, "Error adding document", e)
            }
    }


    companion object {
        private const val TAG = "MainActivity"
    }
}