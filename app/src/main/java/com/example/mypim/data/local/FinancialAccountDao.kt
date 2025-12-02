package com.example.mypim.data.local

import androidx.room.*
import com.example.mypim.data.local.entity.FinancialAccountEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FinancialAccountDao {

    @Query("SELECT * FROM financial_accounts")
    fun getFinancialAccounts(): Flow<List<FinancialAccountEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFinancialAccount(account: FinancialAccountEntity)

    @Delete
    suspend fun deleteFinancialAccount(account: FinancialAccountEntity)
}
