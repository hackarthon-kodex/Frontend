package com.app.kodex.common

data class Chat(
    val role : Role,
    val message : String?
)

enum class Role {
   ADMIN,USER
}