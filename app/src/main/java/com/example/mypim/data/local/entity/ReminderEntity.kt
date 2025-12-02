package com.example.mypim.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reminders")
data class ReminderEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val type: String, // "Periodic", "Triggered", "Scenario-based"
    val trigger: String, // e.g., "Every 30 days", "10% left", "1 day before trip"
    val nextDueDate: Long,
    val isEnabled: Boolean,
)
