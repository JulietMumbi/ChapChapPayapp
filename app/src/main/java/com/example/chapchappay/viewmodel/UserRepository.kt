package com.example.chapchappay.model

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(application: Application) {

    private val db = AppDatabase.getDatabase(application)
    private val userDao = db.userDao()
    private val contributionDao = db.contributionDao()
    private val requestDao = db.requestDao()

    // Insert a new user
    suspend fun insertUser(user: User) = userDao.insertUser(user)

    // Login user
    suspend fun login(email: String, password: String): User? {
        return userDao.login(email, password)
    }

    // Check if user exists by email
    suspend fun getUserByEmail(email: String): User? {
        return userDao.getUserByEmail(email)
    }

    // Register user (returns true if successful, false if email exists)
    suspend fun register(email: String, password: String, role: String): Boolean {
        return withContext(Dispatchers.IO) {
            val existing = getUserByEmail(email)
            if (existing != null) {
                false
            } else {
                val user = User(email = email, password = password, role = role)
                insertUser(user)
                true
            }
        }
    }

    // Dashboard data
    fun getTotalMembers(): LiveData<Int> = userDao.getTotalMembers()
    fun getTotalContributions(): LiveData<Double> = contributionDao.getTotalContributions()
    fun getPendingRequests(): LiveData<List<LoanRequest>> = requestDao.getPendingRequests()

    // Loan requests
    suspend fun insertRequest(request: LoanRequest) = requestDao.insertRequest(request)
    suspend fun updateRequest(request: LoanRequest) = requestDao.updateRequest(request)
}





