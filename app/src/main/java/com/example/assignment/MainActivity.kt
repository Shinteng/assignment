package com.example.assignment
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

data class Section(val name: String?, val time: String?)


class MainActivity : AppCompatActivity() {

    private lateinit var topToolbar: Toolbar
    private lateinit var addButton: Button
    private val sections = mutableListOf<Section>()
    private lateinit var adapter: SectionAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        FirebaseApp.initializeApp(this)

        super.onCreate(savedInstanceState)

        // Check if the user is logged in
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            // User is logged in
            setupMainActivity()
        } else {
            // User is not logged in, show LoginActivity
            setContentView(R.layout.login)
            // ...
        }




        // Initialize views and register button click listeners
        val loginButton: Button = findViewById(R.id.button)
        val registerButton: Button = findViewById(R.id.button2)

        loginButton.setOnClickListener {
            // Handle login button click
            login()
        }

        registerButton.setOnClickListener {
            // Handle register button click
            startActivity(Intent(this, LoginActivity::class.java))

        }

        topToolbar = findViewById(R.id.topToolbar)
        setSupportActionBar(topToolbar)

        addButton = findViewById(R.id.addActivity)
        addButton.setOnClickListener {
            startActivity(Intent(this, AddSectionActivity::class.java))
        }

        topToolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menu_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    true
                }
                R.id.menu_logout -> {
                    FirebaseAuth.getInstance().signOut()
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                    true
                }
                else -> false
            }
        }

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = SectionAdapter(sections)
        recyclerView.adapter = adapter

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    private fun setupMainActivity() {
        setContentView(R.layout.activity_main)

        val sectionsRef = Firebase.firestore.collection("sections")
        sectionsRef.get().addOnSuccessListener { querySnapshot ->
            for (document in querySnapshot.documents) {
                val name = document.getString("name")
                val time = document.getString("time")
                val section = Section(name, time)
                sections.add(section)
            }
            // Notify the adapter that the data has changed
            adapter.notifyDataSetChanged()
        }.addOnFailureListener { exception ->
            // Handle any errors while retrieving data from Firebase
            Toast.makeText(applicationContext, "Failed to retrieve data: ${exception.message}", Toast.LENGTH_SHORT).show()
        }
    }



    private fun login() {
        val phoneNumber = findViewById<EditText>(R.id.editTextPhone).text.toString()
        val password = findViewById<EditText>(R.id.editTextTextPassword).text.toString()

        FirebaseAuth.getInstance().signInWithEmailAndPassword(phoneNumber, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Login successful, navigate to MainActivity
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // Login failed, show error message
                    Toast.makeText(this, "Login failed. Please check your credentials.", Toast.LENGTH_SHORT).show()
                }
            }
    }

}



