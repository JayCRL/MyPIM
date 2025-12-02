package com.example.mypim.ui.screens.accounts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypim.domain.model.FinancialAccount
import com.example.mypim.domain.repository.FinancialAccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddFinancialAccountViewModel @Inject constructor(
    private val financialAccountRepository: FinancialAccountRepository
) : ViewModel() {

    private val _name = MutableStateFlow("")
    val name = _name.asStateFlow()

    private val _type = MutableStateFlow("")
    val type = _type.asStateFlow()

    private val _balance = MutableStateFlow("")
    val balance = _balance.asStateFlow()

    fun onNameChange(newName: String) {
        _name.value = newName
    }

    fun onTypeChange(newType: String) {
        _type.value = newType
    }

    fun onBalanceChange(newBalance: String) {
        _balance.value = newBalance
    }

    fun saveFinancialAccount() {
        viewModelScope.launch {
            val newAccount = FinancialAccount(
                name = _name.value,
                type = _type.value,
                balance = _balance.value.toDoubleOrNull() ?: 0.0
            )
            financialAccountRepository.insertFinancialAccount(newAccount)
        }
    }
}
