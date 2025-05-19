package com.example.config

import com.example.routes.bookRoutes
import io.ktor.server.application.Application

fun Application.configureRouting() {
    bookRoutes()
}