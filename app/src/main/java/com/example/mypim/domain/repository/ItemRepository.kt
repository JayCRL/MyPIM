package com.example.mypim.domain.repository

import com.example.mypim.domain.model.Item
import kotlinx.coroutines.flow.Flow

interface ItemRepository {

    fun getItems(): Flow<List<Item>>

    suspend fun getItemById(id: Int): Item?

    suspend fun insertItem(item: Item)

    suspend fun deleteItem(item: Item)
}
