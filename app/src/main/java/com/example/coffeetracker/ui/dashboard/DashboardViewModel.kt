package com.example.coffeetracker.ui.dashboard

import android.app.Application
import androidx.lifecycle.*
import com.example.coffeetracker.AppDatabase
import com.example.coffeetracker.CoffeeEvent
import com.example.coffeetracker.CoffeeEventRepository
import org.threeten.bp.Duration
import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneId

class DashboardViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: CoffeeEventRepository
    var coffeeCountsByDay: LiveData<Map<Int, Int>>

    init {
        val coffeeEventDao = AppDatabase.getDatabase(application).coffeeEventDao()
        repository = CoffeeEventRepository(coffeeEventDao)

        val today = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toOffsetDateTime()
        val startOfWeek = today - Duration.ofDays(today.dayOfWeek.value.toLong() - 1)

        val coffeesThisWeek = repository.loadAllByTimestamp(
            startOfWeek,
            startOfWeek + Duration.ofDays(7),
        )
        coffeeCountsByDay = Transformations.map(
            coffeesThisWeek
        ) { coffeeList: List<CoffeeEvent> ->
            val weekDayToCoffeeCount: MutableMap<Int, Int> = ((0..6).map {it to 0}).toMap().toMutableMap()

            for (c in coffeeList) {
                val weekDay = c.timestamp.dayOfWeek.value - 1;
                val currentValue = weekDayToCoffeeCount.getOrDefault(weekDay, 0)
                weekDayToCoffeeCount[weekDay] = currentValue + 1
            }

            weekDayToCoffeeCount
        }
    }
}
