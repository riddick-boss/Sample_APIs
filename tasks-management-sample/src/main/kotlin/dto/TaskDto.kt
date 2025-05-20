package com.example.dto

import kotlinx.serialization.Serializable

@Serializable
data class CreateTaskRequest(
    val title: String,
    val description: String,
    val status: Int,
    val priority: Int
)

@Serializable
data class GetTasksResponse(
    val list: List<TaskDto>
)

@Serializable
data class TaskDto(
    val title: String,
    val description: String,
    val status: String,
    val priority: String,
    val createdAt: String
)

@Serializable
data class UpdateTaskRequest(
    val title: String? = null,
    val description: String? = null,
    val status: Int? = null,
    val priority: Int? = null
)

