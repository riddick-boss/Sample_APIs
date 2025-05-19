package com.example.services.book.impl

import com.example.dto.AvailabilityResponse
import com.example.dto.CreateBookRequest
import com.example.dto.BookResponse
import com.example.dto.SearchRequest
import com.example.dto.SearchResponse
import com.example.dto.UpdateBookRequest
import com.example.model.Book
import com.example.repository.BookRepository
import com.example.services.book.BookService
import io.ktor.server.plugins.NotFoundException

class BookServiceImpl(
    private val bookRepository: BookRepository,
    private val mapper: Mapper
) : BookService {
    override fun create(request: CreateBookRequest) {
        val book = mapper.toBook(request)
        bookRepository.save(book)
    }

    override fun get(id: Int): BookResponse {
        val book = bookRepository.get(id) ?: throw NotFoundException("Book $id does not exist!")
        return mapper.toBookResponse(book)
    }

    override fun update(id: Int, request: UpdateBookRequest) {
        val old = bookRepository.get(id) ?: throw NotFoundException("Book $id does not exist!")
        val new = old.copy(
            title = request.title ?: old.title,
            author = request.author ?: old.author,
            isAvailable = request.isAvailable ?: old.isAvailable
        )
        bookRepository.update(new)
    }

    override fun delete(id: Int) {
        bookRepository.delete(id)
    }

    override fun reserve(id: Int) {
        val old = bookRepository.get(id) ?: throw NotFoundException("Book $id does not exist!")
        val new = old.copy(isAvailable = false)
        bookRepository.update(new)
    }

    override fun checkAvailability(id: Int): AvailabilityResponse {
        val isAvailable = bookRepository.get(id)?.isAvailable ?: throw NotFoundException("Book $id does not exist!")
        return AvailabilityResponse(isAvailable)
    }

    override fun search(request: SearchRequest): SearchResponse {
        val list = bookRepository.search(request.filter).map { mapper.toBookResponse(it) }
        return SearchResponse(list)
    }
}

class Mapper() {
    fun toBook(request: CreateBookRequest): Book = Book(
        title = request.title,
        author = request.author
    )

    fun toBookResponse(book: Book): BookResponse = BookResponse(
        title = book.title,
        author = book.author,
        isAvailable = book.isAvailable
    )
}