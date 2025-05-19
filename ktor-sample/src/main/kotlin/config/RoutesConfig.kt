package com.example.config

import com.example.routes.authRoutes
import io.ktor.server.application.Application

fun Application.configureRouting() {
    authRoutes()
}