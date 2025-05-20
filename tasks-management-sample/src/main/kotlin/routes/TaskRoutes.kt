package com.example.routes

import com.example.dto.CreateTaskRequest
import com.example.dto.UpdateTaskRequest
import com.example.services.task.TaskService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.taskRoutes() {
    val taskService by inject<TaskService>()
    routing {
        route("/task") {
            post {
                val requestBody = call.receive<CreateTaskRequest>()
                taskService.create(requestBody)
                call.respond(HttpStatusCode.Created, "Task created!")
            }

            patch("/{id}") {
                val id = call.parameters["id"]?.toInt() ?: throw IllegalArgumentException("Id cannot be null!")
                val requestBody = call.receive<UpdateTaskRequest>()
                taskService.update(id, requestBody)
                call.respond(HttpStatusCode.OK, "Task $id updated!")
            }

            delete("/{id}") {
                val id = call.parameters["id"]?.toInt() ?: throw IllegalArgumentException("Id cannot be null!")
                taskService.delete(id)
                call.respond(HttpStatusCode.OK, "Task deleted!")
            }
        }
        route("/tasks") {
            get {
                val queryParams = call.request.queryParameters
                val status = queryParams["status"]?.toInt()
                val priority = queryParams["priority"]?.toInt()
                val sorted = queryParams["sorted"]?.toBoolean() ?: false
                val response = taskService.get(status, priority, sorted)
                call.respond(response)
            }
        }
    }
}