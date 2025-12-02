package com.example.mypim.domain.model

data class Transaction(
    val id: Int = 0,
    val accountId: Int,
    val amount: Double,
    val isExpense: Boolean,
    val description: String,
    val date: Long,
    val itemId: Int?, // Link to an item
)
