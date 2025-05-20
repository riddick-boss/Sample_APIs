package com.example.services.task

import com.example.dto.CreateTaskRequest
import com.example.dto.GetTasksResponse
import com.example.dto.UpdateTaskRequest

interface TaskService {
    fun create(request: CreateTaskRequest)
    fun get(status: Int?, priority: Int?, sorted: Boolean): GetTasksResponse
    fun update(id: Int, request: UpdateTaskRequest)
    fun delete(id: Int)
}