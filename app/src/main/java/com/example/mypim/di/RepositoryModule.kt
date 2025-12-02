package com.example.mypim.di

import com.example.mypim.data.repository.FinancialAccountRepositoryImpl
import com.example.mypim.data.repository.ItemRepositoryImpl
import com.example.mypim.data.repository.ReminderRepositoryImpl
import com.example.mypim.data.repository.TransactionRepositoryImpl
import com.example.mypim.domain.repository.FinancialAccountRepository
import com.example.mypim.domain.repository.ItemRepository
import com.example.mypim.domain.repository.ReminderRepository
import com.example.mypim.domain.repository.TransactionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindItemRepository(
        itemRepositoryImpl: ItemRepositoryImpl
    ): ItemRepository

    @Binds
    @Singleton
    abstract fun bindFinancialAccountRepository(
        financialAccountRepositoryImpl: FinancialAccountRepositoryImpl
    ): FinancialAccountRepository

    @Binds
    @Singleton
    abstract fun bindTransactionRepository(
        transactionRepositoryImpl: TransactionRepositoryImpl
    ): TransactionRepository

    @Binds
    @Singleton
    abstract fun bindReminderRepository(
        reminderRepositoryImpl: ReminderRepositoryImpl
    ): ReminderRepository
}