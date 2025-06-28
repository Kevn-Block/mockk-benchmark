package com.sparkedember.utils

import java.util.UUID

interface UserServiceWithExtensionFunction

fun UserServiceWithExtensionFunction.getUser(id: String): User {
    return User(id = UUID.fromString(id))
}