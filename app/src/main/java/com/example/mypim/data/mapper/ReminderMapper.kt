package com.example.mypim.data.mapper

import com.example.mypim.data.local.entity.ReminderEntity
import com.example.mypim.domain.model.Reminder as DomainReminder

fun ReminderEntity.toDomainReminder(): DomainReminder {
    return DomainReminder(
        id = this.id,
        title = this.title,
        type = this.type,
        trigger = this.trigger,
        nextDueDate = this.nextDueDate,
        isEnabled = this.isEnabled
    )
}

fun DomainReminder.toReminderEntity(): ReminderEntity {
    return ReminderEntity(
        id = this.id,
        title = this.title,
        type = this.type,
        trigger = this.trigger,
        nextDueDate = this.nextDueDate,
        isEnabled = this.isEnabled
    )
}
