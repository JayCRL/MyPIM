package com.example.mypim.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class ItemEntity(
    @PrimaryKey(autoGenerate = true)
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
