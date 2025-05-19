package com.example.infrastructure

import com.example.model.User

interface PasswordEncoder {
    fun encode(password: String): String
    fun isPasswordValid(user: User, password: String): Boolean
}