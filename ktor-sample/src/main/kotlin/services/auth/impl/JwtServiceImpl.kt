package com.example.services.auth.impl

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.JWTVerifier
import com.example.services.auth.JwtClaims
import com.example.services.auth.JwtService
import io.ktor.server.auth.jwt.JWTCredential
import io.ktor.server.auth.jwt.JWTPrincipal
import java.util.Date

class JwtServiceImpl(
    private val expirationMillis: Long = ONE_WEEK,
    private val secret: String = System.getenv("JWT_SECRET") ?: "jwtSuperSecretToComplyWithHMAC256Algorithm"
) : JwtService {
    override val jwtVerifier: JWTVerifier by lazy {
        JWT
            .require(Algorithm.HMAC256(secret))
            .build()
    }

    override fun generateJwt(email: String): String {
        return JWT.create()
            .withClaim(JwtClaims.EMAIL, email)
            .withExpiresAt(Date(System.currentTimeMillis() + expirationMillis))
            .sign(Algorithm.HMAC256(secret))
    }

    override fun validateJwt(jwtCredential: JWTCredential): JWTPrincipal? {
        val expiryDate = jwtCredential.expiresAt ?: return null
        val email = jwtCredential.payload.getClaim(JwtClaims.EMAIL).asString()
        return if (isBeforeExpiry(expiryDate) && isEmailValid(email)) {
            JWTPrincipal(jwtCredential.payload)
        } else {
            null
        }
    }

    private fun isBeforeExpiry(expiryDate: Date): Boolean {
        val now = Date(System.currentTimeMillis())
        return now.before(expiryDate)
    }

    private fun isEmailValid(email: String?): Boolean = !email.isNullOrBlank()
}

private const val ONE_WEEK = 7 * 24 * 60 * 60 * 1000L