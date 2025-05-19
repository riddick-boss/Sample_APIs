package com.example.services.auth

import com.auth0.jwt.interfaces.JWTVerifier
import io.ktor.server.auth.jwt.JWTCredential
import io.ktor.server.auth.jwt.JWTPrincipal

interface JwtService {
    val jwtVerifier: JWTVerifier
    fun generateJwt(email: String): String
    fun validateJwt(jwtCredential: JWTCredential): JWTPrincipal?
}