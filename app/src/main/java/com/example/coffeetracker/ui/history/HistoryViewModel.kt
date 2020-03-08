package com.example.coffeetracker.ui.history

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.coffeetracker.AppDatabase
import com.example.coffeetracker.CoffeeEvent
import com.example.coffeetracker.CoffeeEventRepository

class HistoryViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: CoffeeEventRepository
    val allCoffeeEvents: LiveData<List<CoffeeEvent>>

    init {
        val coffeeEventDao = AppDatabase.getDatabase(application).coffeeEventDao()
        repository = CoffeeEventRepository(coffeeEventDao)
        allCoffeeEvents = repository.allCoffeeEvents
    }
}
