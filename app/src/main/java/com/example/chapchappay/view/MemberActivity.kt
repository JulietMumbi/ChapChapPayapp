package com.example.chapchappay.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.example.chapchappay.R
import com.example.chapchappay.viewmodel.UserViewModel

class MemberActivity : ComponentActivity() {

    // Initialize ViewModel properly
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member)

        val btnLogout = findViewById<Button>(R.id.btnLogout)
        btnLogout.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        // Find TextViews
        val totalContributionsText = findViewById<TextView>(R.id.totalContributionsText)
        val totalMembersText = findViewById<TextView>(R.id.totalMembersText)
        val pendingRequestsText = findViewById<TextView>(R.id.pendingRequestsText)

        // Observe total contributions
        viewModel.totalContributions.observe(this) { total ->
            totalContributionsText.text = "KSh ${total ?: 0.0}"
        }

        // Observe total members
        viewModel.totalMembers.observe(this) { count ->
            totalMembersText.text = "${count ?: 0} Members"
        }

        // Observe pending requests
        viewModel.pendingRequests.observe(this) { list ->
            pendingRequestsText.text = "${list?.size ?: 0} Pending"
        }
    }
}



