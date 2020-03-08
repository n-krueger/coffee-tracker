package com.example.coffeetracker.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeetracker.AppDatabase
import com.example.coffeetracker.CoffeeEvent
import com.example.coffeetracker.CoffeeEventRepository
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: CoffeeEventRepository

    init {
        val coffeeEventDao = AppDatabase.getDatabase(application).coffeeEventDao()
        repository = CoffeeEventRepository(coffeeEventDao)
    }

    fun insert(vararg coffeeEvents: CoffeeEvent) = viewModelScope.launch {
        repository.insertAll(*coffeeEvents)
    }
}
