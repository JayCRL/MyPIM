package com.example.mypim.domain.model

data class Item(
    val id: Int = 0,
    val name: String,
    val description: String?,
    val photoUri: String?,
    val location: String,
    val locationPhotoUri: String?,
    val status: String, // "In Use", "Idle", "Discarded"
    val purchaseDate: Long,
    val expiryDate: Long?,
    val purchasePrice: Double?,
    val quantity: Int?,
    val minQuantityForReminder: Int?
)
