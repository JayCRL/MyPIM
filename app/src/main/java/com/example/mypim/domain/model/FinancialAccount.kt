package com.example.mypim.domain.model

data class FinancialAccount(
    val id: Int = 0,
    val name: String,
    val type: String, // "Cash", "Bank Account", "Investment", "Virtual Asset"
    val balance: Double,
)
