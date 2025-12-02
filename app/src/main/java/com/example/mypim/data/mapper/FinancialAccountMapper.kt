package com.example.mypim.data.mapper

import com.example.mypim.data.local.entity.FinancialAccountEntity
import com.example.mypim.domain.model.FinancialAccount as DomainFinancialAccount

fun FinancialAccountEntity.toDomainFinancialAccount(): DomainFinancialAccount {
    return DomainFinancialAccount(
        id = this.id,
        name = this.name,
        type = this.type,
        balance = this.balance
    )
}

fun DomainFinancialAccount.toFinancialAccountEntity(): FinancialAccountEntity {
    return FinancialAccountEntity(
        id = this.id,
        name = this.name,
        type = this.type,
        balance = this.balance
    )
}
