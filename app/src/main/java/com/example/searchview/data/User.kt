package com.example.searchview.data

import java.util.*

data class User(
    val name: String,
    val image: Int,
    val description: String,
    val userID: UUID = UUID.randomUUID(),
)
