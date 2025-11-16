package com.example.chapchappay.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.chapchappay.R
import com.example.chapchappay.viewmodel.UserViewModel

class TreasurerActivity : ComponentActivity() {

    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_treasurer)

        // Initialize ViewModel
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[UserViewModel::class.java]

        // Logout button
        val btnLogout = findViewById<Button>(R.id.btnLogout)
        btnLogout.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        // Dashboard TextViews
        val totalContributionsText = findViewById<TextView>(R.id.totalContributionsText)
        val totalMembersText = findViewById<TextView>(R.id.totalMembersText)
        val pendingRequestsText = findViewById<TextView>(R.id.pendingRequestsText)

        // Observe LiveData
        viewModel.totalContributions.observe(this) { total ->
            totalContributionsText.text = "KSh $total"
        }

        viewModel.totalMembers.observe(this) { count ->
            totalMembersText.text = "$count Members"
        }

        viewModel.pendingRequests.observe(this) { requests ->
            pendingRequestsText.text
        }
    }
}


