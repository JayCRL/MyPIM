package com.example.mypim.domain.model

data class Reminder(
    val id: Int = 0,
    val title: String,
    val type: String, // "Periodic", "Triggered", "Scenario-based"
    val trigger: String, // e.g., "Every 30 days", "10% left", "1 day before trip"
    val nextDueDate: Long,
    val isEnabled: Boolean,
)
