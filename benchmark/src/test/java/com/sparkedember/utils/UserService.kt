package com.sparkedember.utils

import java.util.UUID

interface UserService {
    fun getUser(id: String): User
    fun getByEmail(email: String): User
}

class UserServiceImpl : UserService {
    override fun getUser(id: String): User {
        return User(id = UUID.fromString(id))
    }
    override fun getByEmail(email: String): User {
        return User(email = email)
    }
}