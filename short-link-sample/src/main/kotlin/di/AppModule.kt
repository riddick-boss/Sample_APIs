package com.example.di

import com.example.infrastructure.LinkManager
import com.example.infrastructure.impl.UuidLinkManager
import com.example.repository.InMemoryLinkRepository
import com.example.repository.LinkRepository
import com.example.services.link.LinkService
import com.example.services.lin.impl.LinkServiceImpl
import org.koin.dsl.module

val appModule = module {
    single<LinkRepository> { InMemoryLinkRepository() }

    single<LinkService> { LinkServiceImpl(get(), get()) }
    single<LinkManager> { UuidLinkManager() }
}