package com.example.mypim.domain.usecase

import com.example.mypim.domain.model.Item
import com.example.mypim.domain.model.Reminder
import com.example.mypim.domain.model.Transaction
import com.example.mypim.domain.repository.ItemRepository
import com.example.mypim.domain.repository.ReminderRepository
import com.example.mypim.domain.repository.TransactionRepository
import javax.inject.Inject

class SaveItemUseCase @Inject constructor(
    private val itemRepository: ItemRepository,
    private val transactionRepository: TransactionRepository,
    private val reminderRepository: ReminderRepository
) {
    suspend operator fun invoke(item: Item) {
        itemRepository.insertItem(item)

        item.purchasePrice?.let { price ->
            val newTransaction = Transaction(
                accountId = 1, // Default account ID, needs to be dynamic
                amount = price,
                isExpense = true,
                description = "Purchase of ${item.name}",
                date = System.currentTimeMillis(),
                itemId = null // This ID will be 0 until inserted and then updated
            )
            transactionRepository.insertTransaction(newTransaction)
        }

        item.quantity?.let { quantity ->
            item.minQuantityForReminder?.let { minQuantity ->
                if (quantity <= minQuantity) {
                    val newReminder = Reminder(
                        title = "Low stock: ${item.name}",
                        type = "Triggered",
                        trigger = "Quantity below $minQuantity",
                        nextDueDate = System.currentTimeMillis(),
                        isEnabled = true
                    )
                    reminderRepository.insertReminder(newReminder)
                }
            }
        }
    }
}
