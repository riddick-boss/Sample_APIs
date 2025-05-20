package com.example.services.task.impl

import com.example.dto.CreateTaskRequest
import com.example.dto.GetTasksResponse
import com.example.dto.TaskDto
import com.example.dto.UpdateTaskRequest
import com.example.model.Task
import com.example.repository.TaskRepository
import com.example.services.task.TaskService
import io.ktor.server.plugins.NotFoundException

class TaskServiceImpl(
    private val taskRepository: TaskRepository,
    private val mapper: Mapper
) : TaskService {
    override fun create(request: CreateTaskRequest) {
        val task = mapper.toTask(request)
        taskRepository.save(task)
    }

    override fun get(
        status: Int?,
        priority: Int?,
        sorted: Boolean
    ): GetTasksResponse {
        val list = taskRepository.get(
            status = status?.let { mapper.toStatus(it) },
            priority = priority?.let { mapper.toPriority(it) },
            sorted = sorted

        )
        val response = mapper.toGetTasksResponse(list)
        return response
    }

    override fun update(id: Int, request: UpdateTaskRequest) {
        val old = taskRepository.get(id) ?: throw NotFoundException("Task $id not found!")
        val updated = old.copy(
            title = request.title ?: old.title,
            description = request.description ?: old.description,
            status = request.status?.let { mapper.toStatus(it) } ?: old.status,
            priority = request.priority?.let { mapper.toPriority(it) } ?: old.priority
        )
        taskRepository.update(updated)
    }

    override fun delete(id: Int) {
        taskRepository.delete(id)
    }
}

class Mapper() {
    fun toTask(request: CreateTaskRequest): Task = Task(
        title = request.title,
        description = request.description,
        status = request.status.toTaskStatus(),
        priority = request.priority.toTaskPriority()
    )

    fun toStatus(status: Int): Task.Status = status.toTaskStatus()

    fun toPriority(priority: Int): Task.Priority = priority.toTaskPriority()

    fun toGetTasksResponse(list: List<Task>): GetTasksResponse = GetTasksResponse(
        list = list.map { it.toTaskDto() }
    )

    private fun Task.toTaskDto(): TaskDto = TaskDto(
        title = title,
        description = description,
        status = status.name,
        priority = priority.name,
        createdAt = createdAt.toString(),
    )

    private fun Int.toTaskStatus(): Task.Status = when(this) {
        0 -> Task.Status.TO_DO
        1 -> Task.Status.IN_PROGRESS
        2 -> Task.Status.DONE
        else -> throw IllegalArgumentException("Wrong status passed!")
    }

    private fun Int.toTaskPriority(): Task.Priority = when(this) {
        0 -> Task.Priority.LOW
        1 -> Task.Priority.MEDIUM
        2 -> Task.Priority.HIGH
        else -> throw IllegalArgumentException("Wrong priority passed!")
    }
}