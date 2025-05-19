package com.example.infrastructure

interface LinkManager {
    fun createShortId(): String
    fun createShortLink(shortId: String): String
}