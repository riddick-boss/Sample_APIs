package com.example.repository

import com.example.model.Link

interface LinkRepository {
    fun save(link: Link)
    fun get(id: String): Link?
}

class InMemoryLinkRepository : LinkRepository {
    private val links = mutableListOf<Link>()

    override fun save(link: Link) {
        links.add(link)
    }

    override fun get(id: String): Link? {
        return links.firstOrNull { it.id == id }
    }
}