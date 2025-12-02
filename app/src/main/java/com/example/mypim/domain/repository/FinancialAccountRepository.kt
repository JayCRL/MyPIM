package com.example.mypim.domain.repository

import com.example.mypim.domain.model.FinancialAccount
import kotlinx.coroutines.flow.Flow

interface FinancialAccountRepository {

    fun getFinancialAccounts(): Flow<List<FinancialAccount>>

    suspend fun insertFinancialAccount(account: FinancialAccount)

    suspend fun deleteFinancialAccount(account: FinancialAccount)
}
