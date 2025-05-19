package com.example

import com.example.config.configureContentNegotiation
import com.example.config.configureDI
import com.example.config.configureJWTSecurity
import com.example.config.configureMonitoring
import com.example.config.configureRouting
import com.example.config.configureStatusPages
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureDI()
    configureMonitoring()
    configureContentNegotiation()
    configureJWTSecurity()
    configureStatusPages()
    configureRouting()
}
