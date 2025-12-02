package com.example.mypim.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.mypim.CryptoManager
import com.example.mypim.data.local.MyPimDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCryptoManager(): CryptoManager {
        return CryptoManager()
    }

    @Provides
    @Singleton
    fun provideMyPimDatabase(app: Application, @ApplicationContext context: Context, cryptoManager: CryptoManager): MyPimDatabase {
        val passphrase = cryptoManager.getPassphrase(context.filesDir)
        val factory = SupportFactory(passphrase)
        return Room.databaseBuilder(
            app,
            MyPimDatabase::class.java,
            MyPimDatabase.DATABASE_NAME
        )
            .openHelperFactory(factory)
            .build()
    }

    @Provides
    @Singleton
    fun provideItemDao(db: MyPimDatabase) = db.itemDao()

    @Provides
    @Singleton
    fun provideFinancialAccountDao(db: MyPimDatabase) = db.financialAccountDao()

    @Provides
    @Singleton
    fun provideTransactionDao(db: MyPimDatabase) = db.transactionDao()
    
    @Provides
    @Singleton
    fun provideReminderDao(db: MyPimDatabase) = db.reminderDao()
}
