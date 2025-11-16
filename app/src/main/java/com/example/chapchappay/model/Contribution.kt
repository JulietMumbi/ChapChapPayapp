package com.example.chapchappay.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contributions")
data class Contribution(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val memberId: Int,
    val amount: Double,
    val date: String
)
