package com.example.mypim.data.repository

import com.example.mypim.data.local.FinancialAccountDao
import com.example.mypim.data.mapper.toDomainFinancialAccount
import com.example.mypim.data.mapper.toFinancialAccountEntity
import com.example.mypim.domain.model.FinancialAccount as DomainFinancialAccount
import com.example.mypim.domain.repository.FinancialAccountRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FinancialAccountRepositoryImpl @Inject constructor(
    private val financialAccountDao: FinancialAccountDao
) : FinancialAccountRepository {
    override fun getFinancialAccounts(): Flow<List<DomainFinancialAccount>> {
        return financialAccountDao.getFinancialAccounts().map { entities ->
            entities.map { it.toDomainFinancialAccount() }
        }
    }

    override suspend fun insertFinancialAccount(account: DomainFinancialAccount) {
        financialAccountDao.insertFinancialAccount(account.toFinancialAccountEntity())
    }

    override suspend fun deleteFinancialAccount(account: DomainFinancialAccount) {
        financialAccountDao.deleteFinancialAccount(account.toFinancialAccountEntity())
    }
}