package com.example.mypim.ui.screens.accounts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypim.domain.model.FinancialAccount
import com.example.mypim.domain.repository.FinancialAccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FinancialAccountsViewModel @Inject constructor(
    private val financialAccountRepository: FinancialAccountRepository
) : ViewModel() {

    private val _accounts = MutableStateFlow<List<FinancialAccount>>(emptyList())
    val accounts: StateFlow<List<FinancialAccount>> = _accounts

    init {
        getAccounts()
    }

    private fun getAccounts() {
        financialAccountRepository.getFinancialAccounts().onEach { accounts ->
            _accounts.value = accounts
        }.launchIn(viewModelScope)
    }

    fun insertFinancialAccount(account: FinancialAccount) {
        viewModelScope.launch {
            financialAccountRepository.insertFinancialAccount(account)
        }
    }
}
