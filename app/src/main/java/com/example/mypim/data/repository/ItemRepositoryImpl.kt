package com.example.mypim.data.repository

import com.example.mypim.data.local.ItemDao
import com.example.mypim.data.mapper.toDomainItem
import com.example.mypim.data.mapper.toItemEntity
import com.example.mypim.domain.model.Item as DomainItem
import com.example.mypim.domain.repository.ItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ItemRepositoryImpl @Inject constructor(
    private val itemDao: ItemDao
) : ItemRepository {
    override fun getItems(): Flow<List<DomainItem>> {
        return itemDao.getItems().map { itemEntities ->
            itemEntities.map { it.toDomainItem() }
        }
    }

    override suspend fun getItemById(id: Int): DomainItem? {
        return itemDao.getItemById(id)?.toDomainItem()
    }

    override suspend fun insertItem(item: DomainItem) {
        itemDao.insert(item.toItemEntity())
    }

    override suspend fun deleteItem(item: DomainItem) {
        itemDao.delete(item.toItemEntity())
    }
}
