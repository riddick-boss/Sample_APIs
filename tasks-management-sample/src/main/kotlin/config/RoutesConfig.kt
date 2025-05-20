package com.example.config

import com.example.routes.taskRoutes
import io.ktor.server.application.Application

fun Application.configureRouting() {
    taskRoutes()
}