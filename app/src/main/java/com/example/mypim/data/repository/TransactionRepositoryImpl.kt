package com.example.mypim.data.repository

import com.example.mypim.data.local.TransactionDao
import com.example.mypim.data.mapper.toDomainTransaction
import com.example.mypim.data.mapper.toTransactionEntity
import com.example.mypim.domain.model.Transaction as DomainTransaction
import com.example.mypim.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TransactionRepositoryImpl @Inject constructor(
    private val transactionDao: TransactionDao
) : TransactionRepository {
    override fun getTransactions(): Flow<List<DomainTransaction>> {
        return transactionDao.getAllTransactions().map { entities ->
            entities.map { it.toDomainTransaction() }
        }
    }

    override suspend fun getTransactionById(id: Int): DomainTransaction? {
        return transactionDao.getTransactionById(id)?.toDomainTransaction()
    }

    override suspend fun insertTransaction(transaction: DomainTransaction) {
        transactionDao.insert(transaction.toTransactionEntity())
    }

    override suspend fun deleteTransaction(transaction: DomainTransaction) {
        transactionDao.delete(transaction.toTransactionEntity())
    }
}
