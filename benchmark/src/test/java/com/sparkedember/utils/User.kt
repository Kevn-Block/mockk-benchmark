package com.sparkedember.utils

import java.util.UUID

class User(
    private val id: UUID = UUID.randomUUID(),
    val email: String = ""
)