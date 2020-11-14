package com.example.coffeetracker.ui.dashboard

import android.app.Application
import androidx.lifecycle.*
import com.example.coffeetracker.AppDatabase
import com.example.coffeetracker.CoffeeEventRepository
import org.threeten.bp.Duration
import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneId

class DashboardViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: CoffeeEventRepository
    var startOfWeek: MutableLiveData<LocalDate>
    var coffeeCountsByDay: LiveData<Map<Int, Int>>

    init {
        val coffeeEventDao = AppDatabase.getDatabase(application).coffeeEventDao()
        repository = CoffeeEventRepository(coffeeEventDao)
        startOfWeek = MutableLiveData()

        val today = LocalDate.now()
        startOfWeek.value = today.minusDays(today.dayOfWeek.value.toLong() - 1)


        coffeeCountsByDay = Transformations.switchMap(
            startOfWeek
        ) {
            val startTimestamp = it.atStartOfDay(ZoneId.systemDefault()).toOffsetDateTime()
            val coffeesThisWeek = repository.loadAllByTimestamp(
                startTimestamp,
                startTimestamp + Duration.ofDays(7),
            )

            Transformations.map(coffeesThisWeek) { coffeeList ->
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

    fun weekLeft() {
        startOfWeek.value = startOfWeek.value!!.minusDays(7)
    }

    fun weekRight() {
        startOfWeek.value = startOfWeek.value!!.plusDays(7)
    }
}
