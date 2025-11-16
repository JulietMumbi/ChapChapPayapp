package com.example.chapchappay.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.chapchappay.model.LoanRequest
import com.example.chapchappay.model.User
import com.example.chapchappay.model.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = UserRepository(application)

    // Dashboard LiveData
    val totalMembers: LiveData<Int> = repository.getTotalMembers()
    val totalContributions: LiveData<Double> = repository.getTotalContributions()
    val pendingRequests: LiveData<List<LoanRequest>> = repository.getPendingRequests()

    // User LiveData
    val loginUser = MutableLiveData<User?>()
    val registerResult = MutableLiveData<Boolean?>()

    // Insert user (optional)
    fun insertUser(user: User) = viewModelScope.launch {
        repository.insertUser(user)
    }

    // Login
    fun login(email: String, password: String) = viewModelScope.launch {
        try {
            val user = repository.login(email, password)
            loginUser.postValue(user)
        } catch (e: Exception) {
            loginUser.postValue(null)
        }
    }

    fun clearLoginUser() {
        loginUser.value = null
    }

    // Register
    fun register(email: String, password: String, role: String) = viewModelScope.launch {
        try {
            val success = repository.register(email, password, role)
            registerResult.postValue(success)
        } catch (e: Exception) {
            registerResult.postValue(false)
        }
    }

    fun clearRegisterResult() {
        registerResult.value = null
    }

    // Loan operations
    fun submitRequest(request: LoanRequest) = viewModelScope.launch {
        repository.insertRequest(request)
    }

    fun approveRequest(request: LoanRequest) = viewModelScope.launch {
        repository.updateRequest(request.copy(status = "Approved"))
    }

    fun rejectRequest(request: LoanRequest) = viewModelScope.launch {
        repository.updateRequest(request.copy(status = "Rejected"))
    }
}







