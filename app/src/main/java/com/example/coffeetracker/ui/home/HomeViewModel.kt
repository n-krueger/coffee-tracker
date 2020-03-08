package com.example.coffeetracker.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.coffeetracker.AppDatabase
import com.example.coffeetracker.CoffeeEvent
import com.example.coffeetracker.CoffeeEventRepository
import kotlinx.coroutines.launch
import org.threeten.bp.Duration
import org.threeten.bp.LocalDate
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZoneId

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: CoffeeEventRepository
    var coffeeCountToday: LiveData<Int>

    init {
        val coffeeEventDao = AppDatabase.getDatabase(application).coffeeEventDao()
        repository = CoffeeEventRepository(coffeeEventDao)

        val today = LocalDate.now()
        val startOfToday = today.atStartOfDay(ZoneId.systemDefault()).toOffsetDateTime()
        coffeeCountToday = repository.countByTimestamp(
            startOfToday, startOfToday + Duration.ofDays(1)
        )
    }

    private fun insert(vararg coffeeEvents: CoffeeEvent) = viewModelScope.launch {
        repository.insertAll(*coffeeEvents)
    }

    fun addCoffee() {
        val coffeeEvent = CoffeeEvent(OffsetDateTime.now(), "americano")
        insert(coffeeEvent)
    }

    fun onDateChanged() {
        val today = LocalDate.now()
        val startOfToday = today.atStartOfDay(ZoneId.systemDefault()).toOffsetDateTime()
        coffeeCountToday = repository.countByTimestamp(
            startOfToday, startOfToday + Duration.ofDays(1)
        )
    }
}
