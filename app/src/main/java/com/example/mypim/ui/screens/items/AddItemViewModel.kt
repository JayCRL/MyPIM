package com.example.mypim.ui.screens.items

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypim.domain.model.Item
import com.example.mypim.domain.usecase.SaveItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddItemViewModel @Inject constructor(
    private val saveItemUseCase: SaveItemUseCase
) : ViewModel() {

    private val _name = MutableStateFlow("")
    val name = _name.asStateFlow()

    private val _description = MutableStateFlow("")
    val description = _description.asStateFlow()

    private val _location = MutableStateFlow("")
    val location = _location.asStateFlow()

    private val _purchasePrice = MutableStateFlow("")
    val purchasePrice = _purchasePrice.asStateFlow()

    private val _quantity = MutableStateFlow("")
    val quantity = _quantity.asStateFlow()

    private val _minQuantityForReminder = MutableStateFlow("")
    val minQuantityForReminder = _minQuantityForReminder.asStateFlow()

    fun onNameChange(newName: String) {
        _name.value = newName
    }

    fun onDescriptionChange(newDescription: String) {
        _description.value = newDescription
    }

    fun onLocationChange(newLocation: String) {
        _location.value = newLocation
    }

    fun onPurchasePriceChange(newPurchasePrice: String) {
        _purchasePrice.value = newPurchasePrice
    }

    fun onQuantityChange(newQuantity: String) {
        _quantity.value = newQuantity
    }

    fun onMinQuantityForReminderChange(newMinQuantityForReminder: String) {
        _minQuantityForReminder.value = newMinQuantityForReminder
    }

    fun saveItem() {
        viewModelScope.launch {
            val newItem = Item(
                name = _name.value,
                description = _description.value,
                location = _location.value,
                photoUri = null,
                locationPhotoUri = null,
                status = "In Use",
                purchaseDate = System.currentTimeMillis(),
                expiryDate = null,
                purchasePrice = _purchasePrice.value.toDoubleOrNull(),
                quantity = _quantity.value.toIntOrNull(),
                minQuantityForReminder = _minQuantityForReminder.value.toIntOrNull()
            )
            saveItemUseCase(newItem)
        }
    }
}
