package com.example.coffeetracker

import androidx.lifecycle.LiveData
import org.threeten.bp.OffsetDateTime

class CoffeeEventRepository(private val coffeeEventDao: CoffeeEventDao) {
    val allCoffeeEvents: LiveData<List<CoffeeEvent>> = coffeeEventDao.getAll()

    fun loadAllByTimestamp(start: OffsetDateTime, end: OffsetDateTime): LiveData<List<CoffeeEvent>> =
        coffeeEventDao.loadAllByTimestamp(start, end)

    fun countByTimestamp(start: OffsetDateTime, end: OffsetDateTime): LiveData<Int> =
        coffeeEventDao.countByTimestamp(start, end)

    suspend fun insertAll(vararg coffeeEvents: CoffeeEvent) {
        coffeeEventDao.insertAll(*coffeeEvents)
    }
}
