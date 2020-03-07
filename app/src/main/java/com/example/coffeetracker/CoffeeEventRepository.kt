package com.example.coffeetracker

import androidx.lifecycle.LiveData

class CoffeeEventRepository(private val coffeeEventDao: CoffeeEventDao) {
    val allCoffeeEvents: LiveData<List<CoffeeEvent>> = coffeeEventDao.getAll()

    suspend fun insertAll(vararg coffeeEvents: CoffeeEvent) {
        coffeeEventDao.insertAll(*coffeeEvents)
    }
}
