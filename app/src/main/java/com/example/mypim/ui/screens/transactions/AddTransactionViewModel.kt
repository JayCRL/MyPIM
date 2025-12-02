package com.example.mypim.ui.screens.transactions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypim.domain.model.Item
import com.example.mypim.domain.model.Transaction
import com.example.mypim.domain.repository.ItemRepository
import com.example.mypim.domain.repository.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddTransactionViewModel @Inject constructor(
    private val transactionRepository: TransactionRepository,
    private val itemRepository: ItemRepository
) : ViewModel() {

    private val _accountId = MutableStateFlow("")
    val accountId = _accountId.asStateFlow()

    private val _amount = MutableStateFlow("")
    val amount = _amount.asStateFlow()

    private val _isExpense = MutableStateFlow(false)
    val isExpense = _isExpense.asStateFlow()

    private val _description = MutableStateFlow("")
    val description = _description.asStateFlow()

    private val _date = MutableStateFlow(SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date()))
    val date = _date.asStateFlow()

    private val _selectedItemId = MutableStateFlow<Int?>(null)
    val selectedItemId = _selectedItemId.asStateFlow()

    private val _selectedItemName = MutableStateFlow<String?>(null)
    val selectedItemName = _selectedItemName.asStateFlow()

    private val _items = MutableStateFlow<List<Item>>(emptyList())
    val items: StateFlow<List<Item>> = _items

    init {
        itemRepository.getItems().onEach { items ->
            _items.value = items
        }.launchIn(viewModelScope)
    }

    fun onAccountIdChange(newAccountId: String) {
        _accountId.value = newAccountId
    }

    fun onAmountChange(newAmount: String) {
        _amount.value = newAmount
    }

    fun onIsExpenseChange(newIsExpense: Boolean) {
        _isExpense.value = newIsExpense
    }

    fun onDescriptionChange(newDescription: String) {
        _description.value = newDescription
    }

    fun onDateChange(newDate: String) {
        _date.value = newDate
    }

    fun onItemSelected(item: Item) {
        _selectedItemId.value = item.id
        _selectedItemName.value = item.name
    }

    fun saveTransaction() {
        viewModelScope.launch {
            val parsedDate = try {
                SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(_date.value)?.time ?: System.currentTimeMillis()
            } catch (e: Exception) {
                System.currentTimeMillis()
            }

            val newTransaction = Transaction(
                accountId = _accountId.value.toIntOrNull() ?: 0,
                amount = _amount.value.toDoubleOrNull() ?: 0.0,
                isExpense = _isExpense.value,
                description = _description.value,
                date = parsedDate,
                itemId = _selectedItemId.value
            )
            transactionRepository.insertTransaction(newTransaction)
        }
    }
}
