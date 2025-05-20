package com.example.model

import java.time.Instant

data class Task(
    val id: Int = -1,
    val title: String,
    val description: String,
    val status: Status,
    val priority: Priority,
    val createdAt: Instant = Instant.now()
) {
    enum class Status {
        TO_DO, IN_PROGRESS, DONE
    }

    enum class Priority {
        LOW, MEDIUM, HIGH
    }
}
