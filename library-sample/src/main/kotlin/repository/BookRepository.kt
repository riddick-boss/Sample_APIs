package com.example.repository

import com.example.model.Book

interface BookRepository {
    fun save(book: Book)
    fun get(id: Int): Book?
    fun update(book: Book)
    fun delete(id: Int)
    fun search(filter: String): List<Book>
}

class InMemoryBookRepository : BookRepository {
    private val books = mutableListOf<Book>()

    override fun save(book: Book) {
        if (bookAlreadyExists(book)) throw IllegalArgumentException("Book already exists!")

        books.add(book.autoIncrementId())
    }

    override fun get(id: Int): Book? {
        return books.firstOrNull { it.id == id }
    }

    override fun update(book: Book) {
        books.removeIf { it.id == book.id }
        books.add(book)
    }

    override fun delete(id: Int) {
        books.removeIf { it.id == id }
    }

    override fun search(filter: String): List<Book> {
        return books.filter {
            it.title.contains(filter, ignoreCase = true) ||
                    it.author.contains(filter, ignoreCase = true)
        }
    }

    private fun bookAlreadyExists(book: Book): Boolean {
        return get(book.id) != null
    }

    //this method simulates id auto-increment
    private fun Book.autoIncrementId(): Book =
        if (id == -1) {
            val lastId = books.lastOrNull()?.id ?: -1
            copy(id = lastId + 1)
        } else this
}