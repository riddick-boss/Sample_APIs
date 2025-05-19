package com.example.infrastructure.impl

import at.favre.lib.crypto.bcrypt.BCrypt
import com.example.infrastructure.PasswordEncoder
import com.example.model.User

class PasswordEncoderImpl: PasswordEncoder {
    override fun encode(password: String): String {
        //this handles adding salt and hashing password
        return BCrypt.withDefaults().hashToString(10, password.toCharArray())
    }

    override fun isPasswordValid(user: User, password: String): Boolean {
        val result = BCrypt.verifyer().verify(password.toCharArray(), user.password)
        return result.verified
    }
}