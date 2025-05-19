package com.example

import com.example.config.*
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureDI()
    configureMonitoring()
    configureContentNegotiation()
    configureStatusPages()
    configureRouting()
}
