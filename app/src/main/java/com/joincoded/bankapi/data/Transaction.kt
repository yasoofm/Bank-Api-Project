package com.joincoded.bankapi.data

data class Transaction(
    var senderId:Int,
    var receiverId:Int,
    var amount:Double,
    var type: String
)
