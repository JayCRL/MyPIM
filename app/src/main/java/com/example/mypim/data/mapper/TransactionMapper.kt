package com.example.mypim.data.mapper

import com.example.mypim.data.local.entity.TransactionEntity
import com.example.mypim.domain.model.Transaction as DomainTransaction

fun TransactionEntity.toDomainTransaction(): DomainTransaction {
    return DomainTransaction(
        id = this.id,
        accountId = this.accountId,
        amount = this.amount,
        isExpense = this.isExpense,
        description = this.description,
        date = this.date,
        itemId = this.itemId
    )
}

fun DomainTransaction.toTransactionEntity(): TransactionEntity {
    return TransactionEntity(
        id = this.id,
        accountId = this.accountId,
        amount = this.amount,
        isExpense = this.isExpense,
        description = this.description,
        date = this.date,
        itemId = this.itemId
    )
}
