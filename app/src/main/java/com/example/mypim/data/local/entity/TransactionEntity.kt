package com.example.mypim.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val accountId: Int,
    val amount: Double,
    val isExpense: Boolean,
    val description: String,
    val date: Long,
    val itemId: Int?, // Link to an item
)
