package com.example.repository

import com.example.model.Task

interface TaskRepository {
    fun save(task: Task)
    fun get(id: Int): Task?
    fun get(status: Task.Status?, priority: Task.Priority?, sorted: Boolean): List<Task>
    fun update(task: Task)
    fun delete(id: Int)
}

class InMemoryTaskRepository : TaskRepository {
    private val tasks = mutableListOf<Task>()

    override fun save(task: Task) {
        tasks.add(task.autoIncrementId())
    }

    override fun get(id: Int): Task? = tasks.firstOrNull { it.id == id }

    override fun get(status: Task.Status?, priority: Task.Priority?, sorted: Boolean): List<Task> {
        var list = tasks.filter { it.hasRequiredStatus(status) && it.hasRequiredPriority(priority) }
        if (sorted) {
            list = list.sortedByDescending { it.createdAt }
        }
        return list
    }

    override fun update(task: Task) {
        tasks.removeIf { it.id == task.id }
        tasks.add(task)
    }

    override fun delete(id: Int) {
        tasks.removeIf { it.id == id }
    }

    private fun Task.hasRequiredStatus(status: Task.Status?): Boolean = if (status == null) true else this.status == status

    private fun Task.hasRequiredPriority(priority: Task.Priority?): Boolean = if (priority == null) true else this.priority == priority

    //this method simulates id auto-increment
    private fun Task.autoIncrementId(): Task =  if (id == -1) this.copy(id = tasks.size) else this
}