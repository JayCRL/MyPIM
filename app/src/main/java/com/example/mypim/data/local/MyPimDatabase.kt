package com.example.mypim.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mypim.data.local.entity.FinancialAccountEntity
import com.example.mypim.data.local.entity.ItemEntity
import com.example.mypim.data.local.entity.ReminderEntity
import com.example.mypim.data.local.entity.TransactionEntity

@Database(
    entities = [ItemEntity::class, FinancialAccountEntity::class, TransactionEntity::class, ReminderEntity::class],
    version = 1
)
abstract class MyPimDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao
    abstract fun financialAccountDao(): FinancialAccountDao
    abstract fun transactionDao(): TransactionDao
    abstract fun reminderDao(): ReminderDao

    companion object {
        const val DATABASE_NAME = "mypim_db"
    }
}
