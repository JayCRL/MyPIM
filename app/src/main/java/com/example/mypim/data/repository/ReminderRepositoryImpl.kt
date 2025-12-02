package com.example.mypim.data.repository

import com.example.mypim.data.local.ReminderDao
import com.example.mypim.data.mapper.toDomainReminder
import com.example.mypim.data.mapper.toReminderEntity
import com.example.mypim.domain.model.Reminder as DomainReminder
import com.example.mypim.domain.repository.ReminderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReminderRepositoryImpl @Inject constructor(
    private val reminderDao: ReminderDao
) : ReminderRepository {
    override fun getReminders(): Flow<List<DomainReminder>> {
        return reminderDao.getAllReminders().map { entities ->
            entities.map { it.toDomainReminder() }
        }
    }

    override suspend fun insertReminder(reminder: DomainReminder) {
        reminderDao.insert(reminder.toReminderEntity())
    }

    override suspend fun deleteReminder(reminder: DomainReminder) {
        reminderDao.delete(reminder.toReminderEntity())
    }
}