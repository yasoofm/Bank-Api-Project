package com.joincoded.bankapi.data

data class User(
    var username: String,
    var password: String,
    var balance: Double?,
    var image: String?,
    var token: String?
)
