package com.example.infrastructure.impl

import com.example.infrastructure.LinkManager
import java.util.UUID

class UuidLinkManager(
    private val baseUrl: String = System.getenv("base_url")
) : LinkManager {
    override fun createShortId(): String {
        //this gives 16^8 = 4.3 billion combinations - should be enough
        return UUID.randomUUID().toString().substring(0, 8)
    }

    override fun createShortLink(shortId: String): String = "$baseUrl/s/$shortId"
}