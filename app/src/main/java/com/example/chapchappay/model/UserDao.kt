package com.example.chapchappay.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    // User operations
    @Insert
    suspend fun insertUser(user: User)


    @Query("SELECT * FROM members WHERE email = :email AND password = :password")
    suspend fun login(email: String, password: String): User?
    @Query("SELECT * FROM members WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): User?

    @Query("SELECT COUNT(*) FROM members")
    fun getTotalMembers(): LiveData<Int>

    @Query("DELETE FROM members")
    suspend fun clearAllUsers()

}

@Dao
interface ContributionDao {

    @Insert
    suspend fun insertContribution(contribution: Contribution)

    @Query("SELECT SUM(amount) FROM contributions")
    fun getTotalContributions(): LiveData<Double>
}

@Dao
interface RequestDao {

    @Insert
    suspend fun insertRequest(request: LoanRequest)

    @Query("SELECT * FROM requests WHERE status = 'Pending'")
    fun getPendingRequests(): LiveData<List<LoanRequest>>

    @Update
    suspend fun updateRequest(request: LoanRequest)
}

