package com.example.services.lin.impl

import com.example.dto.ShortenRequest
import com.example.dto.ShortenResponse
import com.example.infrastructure.LinkManager
import com.example.model.Link
import com.example.repository.LinkRepository
import com.example.services.link.LinkService
import io.ktor.server.plugins.NotFoundException

class LinkServiceImpl(
    private val linkRepository: LinkRepository,
    private val linkManager: LinkManager
) : LinkService {
    override fun shorten(request: ShortenRequest): ShortenResponse {
        val shortId = linkManager.createShortId()
        val shortLink = linkManager.createShortLink(shortId)
        val linkModel = Link(
            id = shortId,
            originalLink = request.link
        )
        linkRepository.save(linkModel)
        return ShortenResponse(shortLink)
    }

    override fun getOriginal(id: String): String {
        val link = linkRepository.get(id) ?: throw NotFoundException("Link $id does not exist!")
        return link.originalLink
    }
}
