package com.example.config

import com.example.routes.linkRoutes
import io.ktor.server.application.Application

fun Application.configureRouting() {
    linkRoutes()
}