package com.example.config

import com.example.services.auth.JwtService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import org.koin.ktor.ext.inject

fun Application.configureJWTSecurity() {
    val jwtService by inject<JwtService>()

    install(Authentication) {
        jwt(JWT_AUTH) {
            verifier(jwtService.jwtVerifier)
            validate { credential -> jwtService.validateJwt(credential) }
            challenge { _, _ ->
                call.respond(HttpStatusCode.Unauthorized, "Token is not valid or has expired")
            }
        }
    }
}

const val JWT_AUTH = "auth-jwt"