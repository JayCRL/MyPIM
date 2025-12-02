package com.example.mypim.data.mapper

import com.example.mypim.data.local.entity.ItemEntity
import com.example.mypim.domain.model.Item as DomainItem

fun ItemEntity.toDomainItem(): DomainItem {
    return DomainItem(
        id = this.id,
        name = this.name,
        description = this.description,
        photoUri = this.photoUri,
        location = this.location,
        locationPhotoUri = this.locationPhotoUri,
        status = this.status,
        purchaseDate = this.purchaseDate,
        expiryDate = this.expiryDate,
        purchasePrice = this.purchasePrice,
        quantity = this.quantity,
        minQuantityForReminder = this.minQuantityForReminder
    )
}

fun DomainItem.toItemEntity(): ItemEntity {
    return ItemEntity(
        id = this.id,
        name = this.name,
        description = this.description,
        photoUri = this.photoUri,
        location = this.location,
        locationPhotoUri = this.locationPhotoUri,
        status = this.status,
        purchaseDate = this.purchaseDate,
        expiryDate = this.expiryDate,
        purchasePrice = this.purchasePrice,
        quantity = this.quantity,
        minQuantityForReminder = this.minQuantityForReminder
    )
}
