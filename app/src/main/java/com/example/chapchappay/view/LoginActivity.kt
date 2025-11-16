package com.example.chapchappay.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.chapchappay.R
import com.example.chapchappay.viewmodel.UserViewModel

class LoginActivity : ComponentActivity() {

    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize ViewModel
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[UserViewModel::class.java]

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val registerLink = findViewById<TextView>(R.id.registerLink)

        // Login button
        btnLogin.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            if (email.isBlank() || password.isBlank()) {
                etEmail.error = "Fill all fields"
            } else {
                // Coroutine handled in ViewModel
                viewModel.login(email, password)
            }
        }

        // Register link
        registerLink.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        // Observe login result
        viewModel.loginUser.observe(this) { user ->
            if (user != null) {
                val dest = if (user.role.equals("Member", true)) MemberActivity::class.java
                else TreasurerActivity::class.java
                startActivity(Intent(this, dest))
                finish()
                viewModel.clearLoginUser()
            } else {
                etEmail.error = "Invalid email or password"
                viewModel.clearLoginUser()
            }
        }
    }
}








