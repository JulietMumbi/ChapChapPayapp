package com.example.chapchappay.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "requests")
data class LoanRequest(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val memberId: Int,
    val amount: Double,
    val status: String = "Pending" // Pending, Approved, Rejected
)
