package com.example.mypim.ui.screens.items

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypim.domain.model.Item
import com.example.mypim.domain.repository.ItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

import com.example.mypim.domain.model.Item as DomainItem

@HiltViewModel
class ItemsViewModel @Inject constructor(
    private val itemRepository: ItemRepository
) : ViewModel() {

    private val _items = MutableStateFlow<List<DomainItem>>(emptyList())
    val items: StateFlow<List<DomainItem>> = _items

    init {
        getItems()
    }

    private fun getItems() {
        itemRepository.getItems().onEach { items ->
            _items.value = items
        }.launchIn(viewModelScope)
    }

    fun insertItem(item: DomainItem) {
        viewModelScope.launch {
            itemRepository.insertItem(item)
        }
    }
}
