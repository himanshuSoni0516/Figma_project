package com.example.figmaproject

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.figmaproject.databinding.ActivityLoginBinding
import com.example.figmaproject.databinding.ActivitySignupBinding
import com.example.figmaproject.utils.PrefManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnLogin.setOnClickListener {
            val email = binding.editEmail.text.toString()
            val password = binding.editPassword.text.toString()
            if (email.isEmpty()) {
                Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show()
            } else if (!isValidEmail(email)) {
                Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show()
            } else if (password.isEmpty()) {
                Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show()
            } else {
                loginWithFirebase(email,password)
            }

        }
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z0-9+_.-]+@(.+)$")
        return emailRegex.matches(email)
    }

    private fun validatePassword(password: String): Boolean {
        // Define the password validation rules
        val minLength = 8
        val maxLength = 20
        val hasUppercase = password.any { it.isUpperCase() }
        val hasLowercase = password.any { it.isLowerCase() }
        val hasDigit = password.any { it.isDigit() }
        val hasSpecialChar = password.any { !it.isLetterOrDigit() }

        // Check the rules
        val isValidLength = password.length in minLength..maxLength
        val isValid = isValidLength && hasUppercase && hasLowercase && hasDigit && hasSpecialChar

        // Return the validation result
        return isValid
    }

    private fun loginWithFirebase(email : String, password: String){
        setInProgress(true)
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                setInProgress(false)
                val user = Firebase.auth.currentUser
                user?.let {
                    val uid = user.uid
                    val email = user.email
                    val prefManager = PrefManager(this)
                    if (email != null) {
                        prefManager.saveLoginDetails(uid = uid, email = email)
                        startActivity(Intent(this@LoginActivity,FeedActivity::class.java))
                        finish()
                    }

                }

            }
            .addOnFailureListener {
                setInProgress(false)
                Toast.makeText(applicationContext,"Login account failed", Toast.LENGTH_SHORT).show()
            }
    }
    private fun setInProgress(inProgress: Boolean) {
        if (inProgress) {
            binding.progressBar.visibility = View.VISIBLE
            binding.btnLogin.isEnabled = false
            binding.btnLogin.alpha = .5f
        } else {
            binding.progressBar.visibility = View.GONE
            binding.btnLogin.isEnabled = true
            binding.btnLogin.alpha = 1f
        }
    }
}