package com.example.services.link

import com.example.dto.ShortenRequest
import com.example.dto.ShortenResponse

interface LinkService {
    fun shorten(request: ShortenRequest): ShortenResponse
    fun getOriginal(id: String): String
}