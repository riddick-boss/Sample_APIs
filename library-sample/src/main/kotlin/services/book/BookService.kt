package com.example.services.book

import com.example.dto.AvailabilityResponse
import com.example.dto.CreateBookRequest
import com.example.dto.BookResponse
import com.example.dto.SearchRequest
import com.example.dto.SearchResponse
import com.example.dto.UpdateBookRequest

interface BookService {
    fun create(request: CreateBookRequest)
    fun get(id: Int): BookResponse
    fun update(id: Int, request: UpdateBookRequest)
    fun delete(id: Int)
    fun reserve(id: Int)
    fun checkAvailability(id: Int): AvailabilityResponse
    fun search(request: SearchRequest): SearchResponse
}