package com.example.chapchappay.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "members")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val email: String,
    val password: String,
    val role: String   // "member" or "treasurer"
)
