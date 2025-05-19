package com.example.di

import com.example.repository.BookRepository
import com.example.repository.InMemoryBookRepository
import com.example.services.book.BookService
import com.example.services.book.impl.BookServiceImpl
import com.example.services.book.impl.Mapper
import org.koin.dsl.module

val appModule = module {
    single<BookRepository> { InMemoryBookRepository() }

    single<BookService> { BookServiceImpl(get(), get()) }
    single<Mapper> { Mapper() }
}