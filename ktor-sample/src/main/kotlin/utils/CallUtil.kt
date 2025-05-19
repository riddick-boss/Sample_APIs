package com.example.utils

import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.principal
import io.ktor.server.routing.RoutingCall

fun RoutingCall.getPrincipalStringClaim(name: String): String {
    val principal = principal<JWTPrincipal>()!!
    return principal.payload.getClaim(name).asString()
}