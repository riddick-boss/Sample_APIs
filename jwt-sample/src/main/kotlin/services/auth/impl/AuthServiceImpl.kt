package com.example.services.auth.impl

import com.example.dto.LoginRequest
import com.example.dto.LoginResponse
import com.example.dto.RegisterRequest
import com.example.dto.RegisterResponse
import com.example.infrastructure.PasswordEncoder
import com.example.model.User
import com.example.repository.UserRepository
import com.example.services.auth.AuthService
import com.example.services.auth.JwtService
import io.ktor.server.plugins.NotFoundException
import javax.naming.AuthenticationException

class AuthServiceImpl(
    private val userRepository: UserRepository,
    private val jwtService: JwtService,
    private val passwordEncoder: PasswordEncoder
) : AuthService {
    override fun register(request: RegisterRequest): RegisterResponse {
        val user = User(
            email = request.email,
            password = passwordEncoder.encode(request.password)
        )
        userRepository.save(user)
        val jwt = jwtService.generateJwt(user.email)
        return RegisterResponse(jwtToken = jwt)
    }

    override fun login(request: LoginRequest): LoginResponse {
        val user = userRepository.getByEmail(request.email)
        if (user == null) throw NotFoundException()
        if (!passwordEncoder.isPasswordValid(user, request.password)) throw AuthenticationException("Invalid credentials!")
        //auth successful
        val jwt = jwtService.generateJwt(user.email)
        return LoginResponse(jwtToken = jwt)
    }

    override fun verifyUser(email: String) {
        val user = userRepository.getByEmail(email)
        if (user == null) throw NotFoundException()
    }
}