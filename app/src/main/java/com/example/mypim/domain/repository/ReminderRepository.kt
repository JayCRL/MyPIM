package com.example.mypim.domain.repository

import com.example.mypim.domain.model.Reminder
import kotlinx.coroutines.flow.Flow

interface ReminderRepository {

    fun getReminders(): Flow<List<Reminder>>

    suspend fun insertReminder(reminder: Reminder)

    suspend fun deleteReminder(reminder: Reminder)
}
