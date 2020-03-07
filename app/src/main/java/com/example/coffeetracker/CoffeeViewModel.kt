package com.example.coffeetracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CoffeeViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: CoffeeEventRepository
    val allCoffeeEvents: LiveData<List<CoffeeEvent>>

    init {
        val coffeeEventDao = AppDatabase.getDatabase(application).coffeeEventDao()
        repository = CoffeeEventRepository(coffeeEventDao)
        allCoffeeEvents = repository.allCoffeeEvents
    }

    fun insert(vararg coffeeEvents: CoffeeEvent) = viewModelScope.launch {
        repository.insertAll(*coffeeEvents)
    }
}
