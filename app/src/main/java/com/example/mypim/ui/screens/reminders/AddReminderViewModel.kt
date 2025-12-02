package com.example.mypim.ui.screens.reminders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypim.domain.model.Reminder
import com.example.mypim.domain.repository.ReminderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddReminderViewModel @Inject constructor(
    private val reminderRepository: ReminderRepository
) : ViewModel() {

    private val _title = MutableStateFlow("")
    val title = _title.asStateFlow()

    private val _type = MutableStateFlow("")
    val type = _type.asStateFlow()

    private val _trigger = MutableStateFlow("")
    val trigger = _trigger.asStateFlow()

    private val _nextDueDate = MutableStateFlow(SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date()))
    val nextDueDate = _nextDueDate.asStateFlow()

    private val _isEnabled = MutableStateFlow(true)
    val isEnabled = _isEnabled.asStateFlow()

    fun onTitleChange(newTitle: String) {
        _title.value = newTitle
    }

    fun onTypeChange(newType: String) {
        _type.value = newType
    }

    fun onTriggerChange(newTrigger: String) {
        _trigger.value = newTrigger
    }

    fun onNextDueDateChange(newNextDueDate: String) {
        _nextDueDate.value = newNextDueDate
    }

    fun onIsEnabledChange(newIsEnabled: Boolean) {
        _isEnabled.value = newIsEnabled
    }

    fun saveReminder() {
        viewModelScope.launch {
            val parsedDate = try {
                SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(_nextDueDate.value)?.time ?: System.currentTimeMillis()
            } catch (e: Exception) {
                System.currentTimeMillis()
            }

            val newReminder = Reminder(
                title = _title.value,
                type = _type.value,
                trigger = _trigger.value,
                nextDueDate = parsedDate,
                isEnabled = _isEnabled.value
            )
            reminderRepository.insertReminder(newReminder)
        }
    }
}
