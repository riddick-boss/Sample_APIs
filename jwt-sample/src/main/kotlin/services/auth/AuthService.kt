package com.example.services.auth

import com.example.dto.LoginRequest
import com.example.dto.LoginResponse
import com.example.dto.RegisterRequest
import com.example.dto.RegisterResponse

interface AuthService {
    fun register(request: RegisterRequest): RegisterResponse
    fun login(request: LoginRequest): LoginResponse
    fun verifyUser(email: String)
}