package com.example.model

data class Book(
    val id: Int = -1,
    val title: String,
    val author: String,
    val isAvailable: Boolean = true
)
