package com.example.repository

import com.example.model.User
import kotlin.collections.firstOrNull

interface UserRepository {
    fun save(user: User)
    fun getByEmail(email: String): User?
}

class InMemoryUserRepository : UserRepository {
    private val usersList = mutableListOf<User>()

    override fun save(user: User) {
        if (userAlreadyExists(user)) throw IllegalArgumentException("User already exists!")

        usersList.add(user.autoIncrementId())
    }

    override fun getByEmail(email: String): User? {
        return usersList.firstOrNull { it.email == email }
    }

    private fun userAlreadyExists(user: User): Boolean {
        return getByEmail(user.email) != null
    }

    //this method simulates id auto-increment
    private fun User.autoIncrementId(): User =  if (id == -1) this.copy(id = usersList.size) else this
}