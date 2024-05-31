package com.example.figmaproject

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.figmaproject.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.txtLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.btnSignUp.setOnClickListener {
            val name = binding.editName.text
            val email = binding.editEmail.text
            val password = binding.editPassword.text
            if (name.isEmpty()) {
                Toast.makeText(this,"Please enter your name", Toast.LENGTH_SHORT).show()
            } else if (email.isEmpty()) {
                Toast.makeText(this,"Please enter your Email",Toast.LENGTH_SHORT).show()
            }else if (!isValidEmail(email.toString())) {
                Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show()
            } else if (password.isEmpty()) {
                Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show()
            }else if(!validatePassword(password.toString())) {
                Toast.makeText(this, "Please enter a valid password", Toast.LENGTH_SHORT).show()
            }else {
                startActivity(Intent(this, FeedActivity::class.java))
            }
        }
    }
    fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z0-9+_.-]+@(.+)$")
        return emailRegex.matches(email)
    }

    fun validatePassword(password: String): Boolean {
        val minLength = 8
        val maxLength = 20
        val hasUppercase = password.any { it.isUpperCase() }
        val hasLowercase = password.any { it.isLowerCase() }
        val hasDigit = password.any { it.isDigit() }
        val hasSpecialChar = password.any { !it.isLetterOrDigit() }

        // Check if the password meets the required length and complexity requirements
        val isValidLength = password.length in minLength..maxLength
        val isValid = isValidLength && hasUppercase && hasLowercase && hasDigit && hasSpecialChar

        // Return true if the password is valid, false otherwise
        return isValid
    }
}