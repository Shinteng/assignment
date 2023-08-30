package com.example.assignment


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.annotation.SuppressLint
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

        // Initialize views and register button click listener
        val registerButton: Button = findViewById(R.id.button2)

        registerButton.setOnClickListener {
            // Handle register button click
            register()
        }
    }

    @SuppressLint("RestrictedApi")
    private fun register() {
        val phoneNumber = findViewById<EditText>(R.id.editTextPhone).text.toString()
        val password = findViewById<EditText>(R.id.editTextTextPassword).text.toString()
        val confirmPassword = findViewById<EditText>(R.id.editTextTextPassword2).text.toString()

        // Check if passwords match
        if (password == confirmPassword) {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(phoneNumber, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = FirebaseAuth.getInstance().currentUser
                        if (user != null) {
                            val uid = user.uid
                            val userData = mapOf("phoneNumber" to phoneNumber)
                            // Save user data to Firestore
                            FirebaseFirestore.getInstance().collection("users").document(uid)
                                .set(userData)
                                .addOnCompleteListener { task2 ->
                                    if (task2.isSuccessful) {
                                        // Registration successful, navigate to MainActivity
                                        val intent = Intent(this, MainActivity::class.java)
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP) // Clear the activity stack
                                        startActivity(intent)
                                        finish()
                                    } else {
                                        // Registration failed, show error message
                                        Toast.makeText(this, "Failed to save user data.", Toast.LENGTH_SHORT).show()
                                    }
                                }
                        }
                    } else {
                        // Registration failed, show error message
                        Toast.makeText(this, "Registration failed. Please try again.", Toast.LENGTH_SHORT).show()
                    }
                }
        } else {
            // Show error message (passwords don't match)
            Toast.makeText(this, "Passwords do not match. Please try again.", Toast.LENGTH_SHORT).show()
        }
    }

}

