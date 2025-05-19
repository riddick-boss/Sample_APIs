package com.example.routes

import com.example.dto.ShortenRequest
import com.example.services.link.LinkService
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.linkRoutes() {
    val linkService by inject<LinkService>()
    routing {
        get("/short") {
            val requestBody = call.receive<ShortenRequest>()
            val response = linkService.shorten(requestBody)
            call.respond(response)
        }
        get("/s/{id}") {
            val requestBody = call.parameters["id"] ?: throw IllegalArgumentException("Id cannot be null!")
            val originalLink = linkService.getOriginal(requestBody)
            call.respondRedirect(originalLink)
        }
    }
}