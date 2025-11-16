package com.example.chapchappay.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.chapchappay.R
import com.example.chapchappay.viewmodel.UserViewModel

class RegisterActivity : ComponentActivity() {

    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[UserViewModel::class.java]

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val rgRole = findViewById<RadioGroup>(R.id.rgRole)
        val btnRegister = findViewById<Button>(R.id.btnRegister)

        btnRegister.setOnClickListener {
            val email = etEmail.text.toString()
            val pass = etPassword.text.toString()
            val checked = rgRole.checkedRadioButtonId

            if (email.isBlank() || pass.isBlank() || checked == -1) {
                Toast.makeText(this, "Fill all fields and select role", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val role = findViewById<RadioButton>(checked).text.toString()
            viewModel.register(email, pass, role)
        }

        // Observe the registration result
        viewModel.registerResult.observe(this) { success ->
            success?.let {
                if (it) {
                    // Redirect to LoginActivity directly
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                } else {
                    // Optionally, highlight the email EditText for invalid/duplicate
                    etEmail.error = "Email already registered"
                }
                viewModel.clearRegisterResult()
            }
        }

    }
}




