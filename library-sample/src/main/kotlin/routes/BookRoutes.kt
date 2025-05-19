package com.example.routes

import com.example.dto.CreateBookRequest
import com.example.dto.SearchRequest
import com.example.dto.UpdateBookRequest
import com.example.services.book.BookService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.bookRoutes() {
    val bookService by inject<BookService>()
    routing {
        route("/book") {
//            PUBLIC ROUTES
            post {
                val requestBody = call.receive<CreateBookRequest>()
                bookService.create(requestBody)
                call.respond(HttpStatusCode.OK, "Book created!")
            }

            get("/{id}") {
                val bookId = call.parameters["id"]!!.toInt()
                val response = bookService.get(bookId)
                call.respond(response)
            }

            patch("/{id}") {
                val bookId = call.parameters["id"]!!.toInt()
                val requestBody = call.receive<UpdateBookRequest>()
                bookService.update(bookId, requestBody)
                call.respond(HttpStatusCode.OK, "Book $bookId updated!")
            }

            delete("/{id}") {
                val bookId = call.parameters["id"]!!.toInt()
                bookService.delete(bookId)
                call.respond(HttpStatusCode.OK, "Book $bookId deleted!")
            }

            post("/reserve/{id}") {
                val bookId = call.parameters["id"]!!.toInt()
                bookService.reserve(bookId)
                call.respond(HttpStatusCode.OK, "Book $bookId reserved!")
            }

            get("availability/{id}") {
                val bookId = call.parameters["id"]!!.toInt()
                val response = bookService.checkAvailability(bookId)
                call.respond(response)
            }

            get("/search") {
                val requestBody = call.receive<SearchRequest>()
                val response = bookService.search(requestBody)
                call.respond(response)
            }
        }
    }
}